package edu.hitsz.difficulty.mode;

import edu.hitsz.application.AbstractGame;

import java.util.concurrent.TimeUnit;

/**
 * @author 谢岸峰
 */
public class EasyGame extends AbstractGame {

    public EasyGame(){
        //调用父类的构造方法
        super();
    }



    @Override
    public void action(){

        startMusic();
        Runnable task = () -> {
            time += timeInterval;

            if (timeCountAndNewCycleJudge()){
              System.out.println(time);

              creatMobEnemyAndEliteEnemy();

              //飞机射出子弹
              shootAction();
          }

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
    protected void enhanceEnemy() {}

}
