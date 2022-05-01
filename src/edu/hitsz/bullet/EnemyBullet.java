package edu.hitsz.bullet;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.watcher.EnemyObserver;

import java.util.List;

/**
 * @Author hitsz
 */
public class EnemyBullet extends BaseBullet implements EnemyObserver {

    public EnemyBullet(int locationX, int locationY, int speedX, int speedY, int power) {
        super(locationX, locationY, speedX, speedY, power);
    }

    @Override
    public void update(List<AbstractAircraft> enemyAircraft, List<BaseBullet> enemyBullets) {
        for (BaseBullet enemyBullet : enemyBullets){
            enemyBullet.vanish();
        }
    }
}
