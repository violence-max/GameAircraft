package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.watcher.EnemyObserver;

import java.util.List;

/**
 * @author 谢岸峰
 */
public class BoomProp extends AbstractProp  {

    public BoomProp(int locationX,int locationY,int speedX,int speedY){
        super(locationX,locationY,speedX,speedY);
    }

    public void boom(List<AbstractAircraft> enemyAircraft, List<BaseBullet> enemyBullets){
        for (AbstractAircraft enemy : enemyAircraft){
            if (enemy instanceof MobEnemy){
                ((MobEnemy) enemy).update(enemyAircraft,enemyBullets);
            }else if (enemy instanceof EliteEnemy){
                ((EliteEnemy) enemy).update(enemyAircraft,enemyBullets);
            }else if (enemy instanceof BossEnemy){
                ((BossEnemy) enemy).update(enemyAircraft,enemyBullets);
            }
        }
        for (BaseBullet enemyBullet : enemyBullets){
            if (enemyBullet instanceof EnemyBullet){
                ((EnemyBullet) enemyBullet).update(enemyAircraft,enemyBullets);
            }
        }
    }
}
