package edu.hitsz.aircraft;

import edu.hitsz.AircraftStrategy.BossEnemyShoot;
import edu.hitsz.AircraftStrategy.EnemyAircraftShootStrategy;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;

import java.util.LinkedList;
import java.util.List;

public class BossEnemy extends AbstractAircraft {
    public BossEnemy(int locationX,int locationY,int speddX,int speedY,int hp){
        super(locationX,locationY,speddX,speedY,hp);
    }

    private EnemyAircraftShootStrategy bossEnemyShottStrategy = new BossEnemyShoot();
    private EnemyAircraftShootStrategy strategy = bossEnemyShottStrategy;
    private AbstractAircraft bossEnemy;

    public void setBossEnemy(AbstractAircraft bossEnemy) {
        this.bossEnemy = bossEnemy;
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

    @Override
    public List<BaseBullet> shoot() {
        return strategy.shoot(bossEnemy);
    }


}
