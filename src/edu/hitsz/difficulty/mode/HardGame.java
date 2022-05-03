package edu.hitsz.difficulty.mode;

import edu.hitsz.application.AbstractGame;
import edu.hitsz.enemyfactory.BossEnemyProduct;

import java.util.concurrent.TimeUnit;

/**
 * @author 谢岸峰
 */
public class HardGame extends AbstractGame {

    private int bossAddHp = 0;
    private int enemyAddHp = 10;
    private int enemySpeedy = 2;


    public HardGame(){
        //调用父类的构造方法
        super();
    }

    @Override
    public void action() {
        //每10秒增加一架普通敌机或者精英敌机,且敌机属性获得提升
        enemyEnhanceCycleTime = 10*1000;
        enemyEnhanceLastTime = 0;
        //每个射击周期为8秒，直到最低射击周期为止
        shootCycleTimeLast = 8*1000;
        //最低设计周期为0.2秒
        shootCycleLeastTime = 200;
        //随机数边界变化周期为8秒
        boundCycleTime = 8*1000;

        startMusic();
        Runnable task = () -> {
            time += timeInterval;

            if (timeCountAndNewCycleJudge()){
                System.out.println(time);

                //检测是否增加精英敌机产生概率
                boundChange(boundCycleTime);

                //创建普通敌机和精英敌机
                creatMobEnemyAndEliteEnemy();

                //创建boss敌机
                creatBossEnemy();

                //检测是否要增加boss敌机血量
                if (bossAppearFlag && bossAppearTime - bossLastAppearTime >= 1){
                    enhanceBossEnemyHp();
                    bossAppearFlag = false;
                }

                //检测是否增强敌机属性
                if (time - enemyEnhanceLastTime >= enemyEnhanceCycleTime) {
                    //增强敌机属性
                    enhanceEnemy();
                    enemyEnhanceLastTime = time;
                }

            }
            //飞机射出子弹且改变子弹射出频率
            shootCycleAndShootAction();

            //道具移动
            propMoveAction();

            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftMoveAction();

            // 撞击检测
            crashCheckAction();

            // 后处理
            postProcessAction();

            //每个时刻重绘界面
            repaint();

            //游戏结束检查
            gameOverCheck();
        };
        /*
          以固定延迟时间进行执行
          本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);
    }


    private void enhanceBossEnemyHp()  {
        //boss敌机第二次出现之后打印难度提升
        if (bossAppearTime > 1){
            System.out.println("难度提升：boss敌机血量增加"+bossAddHp);
        }
        BossEnemyProduct.bossEnemyHp += bossAddHp;
        bossAddHp += 90;
    }

    @Override
    protected void enhanceEnemy() {
        System.out.println("难度提升：普通敌机、精英敌机和boss敌机血量加："+enemyAddHp+"\n"+"难度提升：普通敌机和精英敌机向下移动速度加："+enemySpeedy);
        changeEnemyHpAndSpeedY(enemyAddHp,enemySpeedy);
        //跨越一个周期则增加血量和移动速度和敌机最大数量
        enemyMaxNumber += 1;
        System.out.println("难度提升：普通敌机、精英敌机数量加一");
        //血量增加10
        enemyAddHp += 10;
        //普通敌机和精英敌机向下移动速度增加2
        enemySpeedy += 2;
    }

}
