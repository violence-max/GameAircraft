package edu.hitsz.aircraft;

import edu.hitsz.aircraft.stratege.BossEnemyShoot;
import edu.hitsz.aircraft.stratege.EnemyAircraftShootStrategy;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.watcher.EnemyObserver;

import java.util.List;

/**
 * @author 谢岸峰
 */
public class BossEnemy extends AbstractAircraft implements EnemyObserver {
    public BossEnemy(int locationX,int locationY,int speedX,int speedY,int hp){
        super(locationX,locationY,speedX,speedY,hp);
    }

    private final EnemyAircraftShootStrategy bossEnemyShotStrategy = new BossEnemyShoot();
    private final EnemyAircraftShootStrategy strategy = bossEnemyShotStrategy;
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

    @Override
    public void update(List<AbstractAircraft> enemyAircraft, List<BaseBullet> enemyBullets) {

    }
}
