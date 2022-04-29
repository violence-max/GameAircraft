package watcher;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

/**
 * @author 谢岸峰
 */
public class EnemysAndTheirBullets implements TobeDelete{
    @Override
    public void update(List<AbstractAircraft> enemyAircrafts, List<BaseBullet> enemyBullets) {
        for(AbstractAircraft enemyAircraft : enemyAircrafts){
            if(enemyAircraft instanceof MobEnemy || enemyAircraft instanceof EliteEnemy){
                //清除普通敌机和英雄机
                enemyAircraft.vanish();
            }
        }
        for(BaseBullet bullet : enemyBullets){
            //清空所有子弹
            bullet.vanish();
        }
    }
}
