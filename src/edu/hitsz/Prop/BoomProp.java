package edu.hitsz.Prop;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;
import watcher.EnemysAndTheirBullets;
import watcher.TobeDelete;

import java.util.List;

public class BoomProp extends AbstractProp  {

    public BoomProp(int locationX,int locationY,int speedX,int speedY){
        super(locationX,locationY,speedX,speedY);
    }

    public void boom(List<AbstractAircraft> enemyAircrafts, List<BaseBullet> enemyBullets){
        TobeDelete delete = new EnemysAndTheirBullets();
        delete.update(enemyAircrafts,enemyBullets);
    }


}
