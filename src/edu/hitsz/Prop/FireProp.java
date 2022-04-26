package edu.hitsz.Prop;

import edu.hitsz.AircraftStrategy.HeroAircraftShoot;
import edu.hitsz.AircraftStrategy.HeroAircraftShootStrategy;
import edu.hitsz.AircraftStrategy.HeroAircraftShootWithFire;
import edu.hitsz.aircraft.HeroAircraft;

import java.util.concurrent.*;

/**
 * @author 谢岸峰
 */
public class FireProp extends AbstractProp {

    public FireProp(int locationX, int locationY, int speedX, int speedY){
        super(locationX,locationY,speedX,speedY);
    }
    private final HeroAircraftShootStrategy heroAircraftShootWithFire = new HeroAircraftShootWithFire();
    private final HeroAircraftShootStrategy heroAircraftShoot = new HeroAircraftShoot();



    /**
     * 线程池，用多线程实现火力道具
     */
    private final ThreadPoolExecutor executorService = new ThreadPoolExecutor(
            1,
            1,
            1000,
            TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(1)
    );

    public void fire(HeroAircraft heroAircraft){
        Runnable task = () -> {
            long start = System.currentTimeMillis();
            long end = start + 10*1000;
            while(System.currentTimeMillis() < end){
                //调用线程的同时开始计时
                heroAircraft.setStrategy(heroAircraftShootWithFire);
            }
            //将英雄机的射击策略调整回直射
            heroAircraft.setStrategy(heroAircraftShoot);
        };
        if (executorService.getTaskCount() == 0){
            //线程池中无线程则添加线程并执行
            executorService.execute(task);
        }else{
            //停止当前线程后立即开启新线程
            executorService.shutdown();
            executorService.execute(task);
        }
    }
}
