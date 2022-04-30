package edu.hitsz.difficulty.mode;

import edu.hitsz.application.AbstractGame;

import java.util.concurrent.TimeUnit;

/**
 * @author 谢岸峰
 */
public class CommonGame extends AbstractGame {

    private int enemyAddHp = 5;
    private int enemySpeedy = 1;


    public CommonGame(){
        //调用父类的构造方法
        super();
    }

    @Override
    public void action() {
        //每20秒增强一次敌机：敌机数量加一，血量、移动速度增加
        enemyEnhanceCycleTime = 20*1000;
        enemyEnhanceLastTime = 0;
        //每个射击周期维持10秒，直到最低射击周期为止
        shootCycleTimeLast = 10*1000;
        //最低射击周期为0.3秒
        shootCycleLeastTime = 300;
        //随机数边界变化周期为10秒
        boundCycleTime = 10*1000;

        startMusic();
        Runnable task = () -> {
            time += timeInterval;

            if (timeCountAndNewCycleJudge()){
                System.out.println(time);

                //检测是否增加精英敌机产生概率
                boundChange(boundCycleTime);

                creatMobEnemyAndEliteEnemy();

                creatBossEnemy();

                //检测是否要增强敌机属性
                if (time - enemyEnhanceLastTime >= enemyEnhanceCycleTime) {
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



    @Override
    protected void enhanceEnemy(){
        changeEnemyHpAndSpeedY(enemyAddHp,enemySpeedy);
        System.out.println("难度提升：普通敌机、精英敌机和boss敌机血量加："+enemyAddHp+"\n"+"难度提升：普通敌机和精英敌机向下移动速度加："+enemySpeedy);
        //跨越一个周期则增加血量和移动速度和敌机最大数量
        enemyMaxNumber += 1;
        enemyAddHp += 5;
        enemySpeedy += 1;
    }

}
