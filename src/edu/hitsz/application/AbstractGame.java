package edu.hitsz.application;

import edu.hitsz.bgm.MusicAction;
import edu.hitsz.source.data.DataPatternDemo;
import edu.hitsz.prop.*;
import edu.hitsz.aircraft.*;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
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
public abstract class AbstractGame extends JPanel {


    /**
     * Scheduled 线程池，用于任务调度
     */
    protected final ScheduledExecutorService executorService;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    protected int timeInterval = 40;

    protected final HeroAircraft heroAircraft;
    protected final List<AbstractAircraft> enemyAircraft;
    protected final List<BaseBullet> heroBullets;
    protected final List<BaseBullet> enemyBullets;
    protected final List<AbstractProp> props;

    protected int enemyMaxNumber = 6;

    protected boolean gameOverFlag = false;
    public static int score = 0;
    protected int time = 0;

    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    protected int cycleDuration = 600;
    protected int cycleTime = 0;

    /**
     *随机数（0~1）（整型）
     * temp1用于随机产生普通敌机或者精英敌机
     */
    protected Random r = new Random();
    protected int temp1;


    /**
     * bossisexitflag为布尔类型的判断标志，用于判断boss敌机是否已经存在
     * fscore为记录上一次boss敌机出现时的得分，初始值设置为0
     * bossAppear为boss敌机出现的阈值
     */
    protected boolean bossIsExistFlag;
    protected int fakeScore = 0;
    protected int bossAppear = 1000;

    /**
     *创建一个背景音乐对象
     */
    protected MusicAction musicAction = new MusicAction();


    /**
     * 构建一个访问数据对象
     */
    public static DataPatternDemo dataPatternDemo = new DataPatternDemo();

    /**
     * 创建敌机的对象
     */
    protected CreateEnemyAircraft createEnemyAircraft = new CreateEnemyAircraft();
    /**
     * 上一次敌机得到增强的时间，起初为0
     */
    protected int enemyEnhanceLastTime = 0;
    /**
     * 每次敌机增强后维持的周期
     */
    protected int enemyEnhanceCycleTime;
    /**
     * 记录boss敌机出现的次数
     */
    protected int bossAppearTime = 0;
    /**
     * 记录boss敌机上次出现是第几次出现
     */
    protected int bossLastAppearTime;
    /**
     * 为困难模式下boss敌机每次出现血量较上一次增加设置的标志位
     */
    protected boolean bossAppearFlag = false;

    /**
     * 起初的射击周期，0.6s
     */
    protected int shootCycleTime = 600;
    /**
     * 射击时间
     */
    protected int shootTime = 0;

    /**
     * 每个射击周期
     */
    protected int shootCycleTimeLast;

    /**
     * 射击周期改变
     */
    protected int shootChangeTime = 0;

    /**
     * 最低射击周期
     */
    protected int shootCycleLeastTime;
    /**
     * 随机数边界
     */
    protected int bound = 2;
    /**
     * 上一次随机数边界发生改变的时间
     */
    protected int boundLastChangeTime = 0;

    /**
     * 随机数边界维持时间为10秒
     */
    protected int boundCycleTime;

    public AbstractGame() {
        heroAircraft = HeroAircraft.getHeroAircraft();
        enemyAircraft = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        props = new LinkedList<>();

        //Scheduled 线程池，用于定时任务调度
        executorService = new ScheduledThreadPoolExecutor(1);

        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);
    }


    /**
     * 增强敌机属性：血量、向下飞行速度
     */
    protected abstract void enhanceEnemy();

    /**
     * 改变敌机工厂中的血量和速度属性
     */
    protected void changeEnemyHpAndSpeedY(int enemyAddHp,int enemySpeedy){
        //所有敌机血量加5
        BossEnemyProduct.bossEnemyHp += enemyAddHp;
        EliteEnemyProduct.eliteEnemyHp += enemyAddHp;
        MobEnemyProduct.mobEnemyHp += enemyAddHp;
        //普通敌机和精英敌机向下移动速度加一
        EliteEnemyProduct.eliteSpeedY += enemySpeedy;
        MobEnemyProduct.mobEnemySpeedY += enemySpeedy;
    }



    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public abstract void action();


    /**
     * 控制射击周期以及射击动作
     */
    protected void shootCycleAndShootAction(){
        if (time - shootTime >= shootCycleTime){
            //飞机射出子弹
            shootAction();
            shootTime = time;
            if (time - shootChangeTime >= shootCycleTimeLast && shootCycleTime >= shootCycleLeastTime){
                //射击周期减少0.02秒
                shootCycleTime -= 20;
                System.out.println("难度提升，射击周期减短为："+shootCycleTime+"毫秒");
                shootChangeTime = time;
            }
        }
    }

    /**
     * 创建普通敌机和精英敌机
     */
    protected void creatMobEnemyAndEliteEnemy(){
        // 普通敌机和精英敌机产生
        temp1 = r.nextInt(bound);
        if (enemyAircraft.size() < enemyMaxNumber) {
            //普通敌机
            if(temp1 == 0){
                MobEnemy mobEnemy = createEnemyAircraft.creatMobEnemy();
                enemyAircraft.add(mobEnemy);
            }
            //精英敌机
            else{
                EliteEnemy eliteEnemy = createEnemyAircraft.createEliteEnemy();
                enemyAircraft.add(eliteEnemy);
            }
        }
    }

    /**
     * 增加精英敌机产生概率
     */
    protected void boundChange(int boundCycleTime){
        if (time - boundLastChangeTime >= boundCycleTime){
            //精英敌机产生概率提高
            bound += 1;
            int lastBound = bound - 1;
            System.out.println("难度提升：精英敌机产生概率提高为："+bound+"分之"+lastBound);
            boundLastChangeTime = time;
        }
    }

    /**
     * 创建boss敌机
     */
    protected void creatBossEnemy(){
        /**
         * 以1000分为阈值，超过则新建一架boss敌机
         */
        if (score - fakeScore >= bossAppear && enemyAircraft.size() < enemyMaxNumber){
            bossLastAppearTime = bossAppearTime;
            bossAppearTime += 1;
            bossIsExistFlag = true;
            fakeScore = score;
        }

        if (bossIsExistFlag){
            //创建boss敌机
            BossEnemy bossEnemy = createEnemyAircraft.creatBossEnemy();
            enemyAircraft.add(bossEnemy);
            bossIsExistFlag = false;

            if (Main.IS_MUSIC){
                //关闭背景音乐
                musicAction.setGameBgmFlag(false);
                musicAction.gameBgm();
                //开启boss背景音乐
                musicAction.setBossBgmFlag(true);
                musicAction.bossBgm();
            }
        }
    }



    /**
     * 用于一开始播放音乐
     */
    protected void startMusic(){
        if (Main.IS_MUSIC){
            musicAction.setGameBgmFlag(true);
            musicAction.gameBgm();
        }
    }

    /**
     * 游戏结束检查
     */
    protected void gameOverCheck(){
        if (heroAircraft.getHp() <= 0) {
            if (Main.IS_MUSIC){
                musicAction.gameOverBgm();

                //关闭游戏背景音乐
                musicAction.setGameBgmFlag(false);
                musicAction.gameBgm();
                ///关闭boss敌机背景音乐
                musicAction.setBossBgmFlag(false);
                musicAction.bossBgm();
            }

            //打印得分排行榜
            dataPatternDemo.fileAction(score);

            // 游戏结束
            executorService.shutdown();
            gameOverFlag = true;
            System.out.println("Game Over!");
            synchronized (Main.class){
                Main.class.notify();
            }
        }
    }



    //***********************
    //      Action 各部分
    //***********************

    protected boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }


    protected void shootAction() {
        // TODO 敌机射击
        for (AbstractAircraft enemyAircraft : enemyAircraft){
            if (enemyAircraft instanceof EliteEnemy){
                ((EliteEnemy) enemyAircraft).setEnemyAircraft(enemyAircraft);
                enemyBullets.addAll(enemyAircraft.shoot());
            }
            else if (enemyAircraft instanceof BossEnemy){
                ((BossEnemy) enemyAircraft).setBossEnemy(enemyAircraft);
                enemyBullets.addAll(enemyAircraft.shoot());
            }
            else if(enemyAircraft instanceof MobEnemy){
                ((MobEnemy) enemyAircraft).setMobEnemy(enemyAircraft);
                enemyBullets.addAll(enemyAircraft.shoot());
            }
        }
        // 英雄射击
        heroBullets.addAll(heroAircraft.shoot());
        if (Main.IS_MUSIC){
            musicAction.shootBgm();
        }
    }

    protected void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    protected void propMoveAction(){
        for (AbstractProp prop : props){
            prop.forward();
        }
    }

    protected void aircraftMoveAction() {
        for (AbstractAircraft enemyAircraft : enemyAircraft) {
            enemyAircraft.forward();
        }
    }

    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    protected void crashCheckAction() {
        // TODO 敌机子弹攻击英雄
        for (BaseBullet fakeBullet : enemyBullets){
            if (fakeBullet.notValid()){
                continue;
            }
            if(heroAircraft.crash(fakeBullet)){
                //英雄机撞击到敌机子弹
                //英雄机损失一定生命值
                heroAircraft.decreaseHp(fakeBullet.getPower());
                fakeBullet.vanish();
            }
        }
        // 英雄子弹攻击敌机
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (AbstractAircraft enemyAircraft : enemyAircraft) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    if (Main.IS_MUSIC){
                        //播放英雄击中敌机的bgm
                        musicAction.shootHitBgm();
                    }
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();
                    if (enemyAircraft.notValid()) {
                        // TODO 获得分数，产生道具补给
                        CreatProps creatProps = new CreatProps();
                        if(enemyAircraft instanceof EliteEnemy || enemyAircraft instanceof BossEnemy){
                            int temp2 = r.nextInt(4);
                            if (temp2 == 0){
                                //产生恢复hp道具
                                HpProp hp = creatProps.creatHpProp(enemyAircraft);
                                props.add(hp);
                            }
                            //产生增强火力道具
                            else if(temp2 == 1){
                                FireProp fire = creatProps.creatFireProp(enemyAircraft);
                                props.add(fire);
                            }
                            //产生炸弹道具
                            else if(temp2 == 2){
                                BoomProp boom = creatProps.creatBoomProp(enemyAircraft);
                                props.add(boom);
                            }
                        }
                        /*
                          消灭普通敌机加10分
                          消灭精英敌机加20分
                          消灭boss敌机加200分
                         */
                        if (enemyAircraft instanceof MobEnemy){
                            score += 10;
                        }
                        else if (enemyAircraft instanceof  EliteEnemy){
                            score += 20;
                        }
                        else{
                            if (Main.IS_MUSIC){
                                //开始播放游戏背景音乐
                                musicAction.setGameBgmFlag(true);
                                musicAction.gameBgm();
                                //停止播放boss敌机背景音乐
                                musicAction.setBossBgmFlag(false);
                                musicAction.bossBgm();
                            }
                            score += 200;
                        }
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
                    heroAircraft.decreaseHp(((HpProp) prop).increaseHp());
                }else if(prop instanceof BoomProp){
                    if (Main.IS_MUSIC){
                        //播放获取炸弹道具的背景音乐
                        musicAction.bombExplosion();
                    }
                    //炸弹道具生效
                    ((BoomProp) prop).boom(enemyAircraft,enemyBullets);
                }else if(prop instanceof FireProp){
                    if (Main.IS_MUSIC){
                        //播放获取火力道具的背景音乐
                        musicAction.getSupply();
                    }
                    //火力道具生效
                    ((FireProp) prop).fire(heroAircraft);
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
    protected void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircraft.removeIf(AbstractFlyingObject::notValid);
        props.removeIf(AbstractFlyingObject::notValid);
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
        int backGroundTop = 0;

        super.paint(g);

        // 绘制背景,图片滚动
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, backGroundTop, null);
        backGroundTop += 1;
        if (backGroundTop == Main.WINDOW_HEIGHT) {
            backGroundTop = 0;
        }

        // 先绘制子弹，后绘制飞机,再绘制道具
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);

        paintImageWithPositionRevised(g, enemyAircraft);
        paintImageWithPositionRevised(g, props);

        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);

    }

    protected void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    protected void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + score, x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
    }

}
