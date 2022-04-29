package edu.hitsz.watcher;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.application.Game;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

/**
 * @author 谢岸峰
 */
public class EnemysAndTheirBullets implements TobeDelete{
    @Override
    public void update(List<AbstractAircraft> enemyAircrafts, List<BaseBullet> enemyBullets) {
        for(AbstractAircraft enemyAircraft : enemyAircrafts){
            if(enemyAircraft instanceof MobEnemy){
                Game.score += 10;
                //清除普通敌机并加10分
                enemyAircraft.vanish();
            }else if (enemyAircraft instanceof  EliteEnemy){
                Game.score += 20;
                //清楚精英敌机并加20分
                enemyAircraft.vanish();
            }
        }
        for(BaseBullet bullet : enemyBullets){
            //清空所有子弹
            bullet.vanish();
        }
    }
}
