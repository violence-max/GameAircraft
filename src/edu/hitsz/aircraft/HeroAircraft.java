package edu.hitsz.aircraft;


import edu.hitsz.aircraft.stratege.HeroAircraftShoot;
import edu.hitsz.aircraft.stratege.HeroAircraftShootStrategy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

/**
 * 英雄飞机，游戏玩家操控
 * @author hitsz
 */
public class HeroAircraft extends AbstractAircraft {


    /**
     * 创建唯一英雄机
     */
    public static HeroAircraft heroAircraft = new HeroAircraft(
            Main.WINDOW_WIDTH / 2,
            Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight() ,
            0, 0, 1000);

    private final HeroAircraftShootStrategy heroAircraftShoot = new HeroAircraftShoot();
    private HeroAircraftShootStrategy strategy = heroAircraftShoot;


    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp    初始生命值
     */
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    public void setStrategy(HeroAircraftShootStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * lazy方式
     * @return
     */
    public static synchronized HeroAircraft getHeroAircraft(){
        if (heroAircraft == null){
            heroAircraft = new HeroAircraft(
                    Main.WINDOW_WIDTH / 2,
                    Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight() ,
                    0, 0, 1000);
        }
        return heroAircraft;
    }

    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }

    @Override
    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    public List<BaseBullet> shoot() {
        return strategy.shoot(heroAircraft);
    }

}
