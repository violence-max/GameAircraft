package edu.hitsz.aircraft;

import edu.hitsz.AircraftStrategy.EnemyAircraftShootStrategy;
import edu.hitsz.AircraftStrategy.MobEnemyShoot;
import edu.hitsz.application.Game;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;

import java.util.LinkedList;
import java.util.List;

/**
 * 普通敌机
 * 不可射击
 *
 * @author hitsz
 */
public class MobEnemy extends AbstractAircraft {

    public MobEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    private EnemyAircraftShootStrategy mobEnemyShootStrategy = new MobEnemyShoot();
    private EnemyAircraftShootStrategy strategy = mobEnemyShootStrategy;
    private AbstractAircraft mobEnemy;

    public void setMobEnemy(AbstractAircraft mobEnemy) {
        this.mobEnemy = mobEnemy;
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT) {
            vanish();
        }
    }

    @Override
    public List<BaseBullet> shoot() {
        return strategy.shoot(mobEnemy);
    }
}


