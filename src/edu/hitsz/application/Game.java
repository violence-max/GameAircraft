package edu.hitsz.application;

import edu.hitsz.Prop.*;
import edu.hitsz.aircraft.*;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstrcatFlyingObject;
import edu.hitsz.enemyfactory.*;
import edu.hitsz.propfactory.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

/**
 * 游戏主面板，游戏启动
 *
 * @author hitsz
 */
public class Game extends JPanel {

    private int backGroundTop = 0;

    /**
     * Scheduled 线程池，用于任务调度
     */
    private final ScheduledExecutorService executorService;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    private int timeInterval = 40;

    private final HeroAircraft heroAircraft;
    private final List<AbstractAircraft> enemyAircrafts;
    private final List<BaseBullet> heroBullets;
    private final List<BaseBullet> enemyBullets;
    private final List<AbstractProp> props;

    private int enemyMaxNumber = 5;

    private boolean gameOverFlag = false;
    private int score = 0;
    private int time = 0;
    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    private int cycleDuration = 600;
    private int cycleTime = 0;

    /**
     *随机数（0~1）（整型）
     * temp1用于随机产生普通敌机或者精英敌机
     * temp2用于精英敌机被消灭后随机产生三种道具
     */
    Random r = new Random();
    private int temp1;
    private int temp2;


    public Game() {
        heroAircraft = HeroAircraft.getheroaircraft();

        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        props = new LinkedList<>();

        //Scheduled 线程池，用于定时任务调度
        executorService = new ScheduledThreadPoolExecutor(1);

        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);

    }

    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public void action() {

        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {

            time += timeInterval;

            /**
             * 创建精英敌机
             */
            EliteEnemy feliteenemy = createliteenemy();
            MobEnemy fmobenemy = creatmobenemy();

            // 周期性执行（控制频率）
            if (timeCountAndNewCycleJudge()) {
                temp1 = r.nextInt(2);
                System.out.println(time);
                // 敌机产生
                if (enemyAircrafts.size() < enemyMaxNumber) {
                    //普通敌机
                    if(temp1 == 0){
                        enemyAircrafts.add(fmobenemy);
                    }
                    //精英敌机
                    else{

                        enemyAircrafts.add(feliteenemy);
                        /**
                         * 精英敌机射出子弹
                         */
                        enemyBullets.addAll(feliteenemy.shoot());
                    }

                }
                // 飞机射出子弹
                shootAction();
            }
            //道具移动
            propMoveAction();

            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            // 撞击检测
            crashCheckAction();

            // 后处理
            postProcessAction();

            //每个时刻重绘界面
            repaint();

            // 游戏结束检查
            if (heroAircraft.getHp() <= 0) {
                // 游戏结束
                executorService.shutdown();
                gameOverFlag = true;
                System.out.println("Game Over!");
            }

        };

        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

    }

    //***********************
    //      Action 各部分
    //***********************

    private boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    private  EliteEnemy createliteenemy(){
        EnemyAircraftFactory eliteenemyfactory;
        EnemyAircraftProduct eliteenemyproduct;

        eliteenemyfactory = new CreatEliteEnemy();
        eliteenemyproduct = eliteenemyfactory.creatEnemyProduct();
        return eliteenemyproduct.createliteenemy();
    }

    private  MobEnemy creatmobenemy(){
        EnemyAircraftFactory mobenemyfactory;
        EnemyAircraftProduct mobenemyproduct;

        mobenemyfactory = new CreatMobeEnemy();
        mobenemyproduct = mobenemyfactory.creatEnemyProduct();
        return mobenemyproduct.creatmobenemy();
    }

    private void shootAction() {
        // TODO 敌机射击
        //代码写在了创建精英敌机处，更方便
        // 英雄射击
        heroBullets.addAll(heroAircraft.shoot());
    }

    private void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void propMoveAction(){
        for (AbstractProp prop : props){
            prop.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }

    private HpProp creathpprop(AbstractAircraft enemyaircraft) {
        int x = enemyaircraft.getLocationX();
        int y = enemyaircraft.getLocationY();
        int speedX = 0;
        int speedY = enemyaircraft.getSpeedY();

        PropFactory hppropfactory;
        PropProduct hppropproduct;

        hppropfactory = new CreatHpProp();
        hppropproduct= hppropfactory.creatPropProduct(x,y,speedX,speedY);
        return hppropproduct.creathpprop(x,y,speedX,speedY);
    }

    private FireProp creatfireprop(AbstractAircraft enemyaircraft) {
        int x = enemyaircraft.getLocationX();
        int y = enemyaircraft.getLocationY();
        int speedX = 0;
        int speedY = enemyaircraft.getSpeedY();

        PropFactory firepropfactory;
        PropProduct firepropproduct;

        firepropfactory = new CreatFireProp();
        firepropproduct= firepropfactory.creatPropProduct(x,y,speedX,speedY);
        return firepropproduct.creatfireprop(x,y,speedX,speedY);
    }

    private BoomProp creatboomprop(AbstractAircraft enemyaircraft) {
        int x = enemyaircraft.getLocationX();
        int y = enemyaircraft.getLocationY();
        int speedX = 0;
        int speedY = enemyaircraft.getSpeedY();

        PropFactory boompropfactory;
        PropProduct boompropproduct;

        boompropfactory = new CreatBoomProp();
        boompropproduct= boompropfactory.creatPropProduct(x,y,speedX,speedY);
        return boompropproduct.creatboomprop(x,y,speedX,speedY);
    }

    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        // TODO 敌机子弹攻击英雄
        for (BaseBullet fbullet : enemyBullets){
            if (fbullet.notValid()){
                continue;
            }
            if(heroAircraft.crash(fbullet)){
                //英雄机撞击到敌机子弹
                //英雄机损失一定生命值
                heroAircraft.decreaseHp(fbullet.getPower());
                fbullet.vanish();
            }
        }

        // 英雄子弹攻击敌机
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (AbstractAircraft enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();
                    if (enemyAircraft.notValid()) {
                        // TODO 获得分数，产生道具补给
                        if(enemyAircraft instanceof EliteEnemy){
                            temp2 = r.nextInt(4);
                            if (temp2 == 0){
                                //产生恢复hp道具
                                HpProp hp = creathpprop(enemyAircraft);
                                props.add(hp);
                            }
                            //产生增强火力道具
                            else if(temp2 == 1){
                                FireProp fire = creatfireprop(enemyAircraft);
                                props.add(fire);
                            }
                            //产生炸弹道具
                            else if(temp2 == 2){
                                BoomProp boom = creatboomprop(enemyAircraft);
                                props.add(boom);
                            }else{
                                ;
                            }
                        }
                        score += 10;
                    }
                }
                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }


        // Todo: 我方获得道具，道具生效
        for(AbstractProp prop : props){
            if (prop.crash(heroAircraft )|| heroAircraft.crash(prop)){
                if(prop instanceof HpProp){
                    heroAircraft.decreaseHp(((HpProp) prop).increasehp());
                }else if(prop instanceof BoomProp){
                    ((BoomProp) prop).boom();
                }else if(prop instanceof FireProp){
                    ((FireProp) prop).fire();
                }else{
                    ;
                }
                prop.vanish();
            }
        }

    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * 3. 检查英雄机生存
     * 4.删除无效的道具
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    private void postProcessAction() {
        enemyBullets.removeIf(AbstrcatFlyingObject::notValid);
        heroBullets.removeIf(AbstrcatFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstrcatFlyingObject::notValid);
        props.removeIf(AbstrcatFlyingObject::notValid);
    }


    //***********************
    //      Paint 各部分
    //***********************

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     *
     * @param  g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 绘制背景,图片滚动
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，后绘制飞机,再绘制道具
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);

        paintImageWithPositionRevised(g, enemyAircrafts);
        paintImageWithPositionRevised(g, props);

        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);

    }

    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstrcatFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstrcatFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + this.score, x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
    }


}
