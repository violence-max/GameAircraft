package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.watcher.EnemyAndTheirBullets;
import edu.hitsz.watcher.TobeDelete;

import java.util.List;

/**
 * @author 谢岸峰
 */
public class BoomProp extends AbstractProp  {

    public BoomProp(int locationX,int locationY,int speedX,int speedY){
        super(locationX,locationY,speedX,speedY);
    }

    public void boom(List<AbstractAircraft> enemyAircraft, List<BaseBullet> enemyBullets){
        TobeDelete delete = new EnemyAndTheirBullets();
        delete.update(enemyAircraft,enemyBullets);
    }
}
