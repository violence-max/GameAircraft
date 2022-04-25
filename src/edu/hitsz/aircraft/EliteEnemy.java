package edu.hitsz.aircraft;

import edu.hitsz.AircraftStrategy.EliteEnemyShoot;
import edu.hitsz.AircraftStrategy.EnemyAircraftShootStrategy;
import edu.hitsz.application.Game;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

/**
 * 精英敌机
 * 
 */
public class EliteEnemy extends AbstractAircraft{


    public EliteEnemy(int locationX,int locationY,int speedX,int speedY,int hp){
        super(locationX,locationY,speedX,speedY,hp);
    }

    private EnemyAircraftShootStrategy eilteEnemyShootStrategy = new EliteEnemyShoot();
    private EnemyAircraftShootStrategy strategy = eilteEnemyShootStrategy;
    private AbstractAircraft enemyAircraft;


    public void setEnemyAircraft(AbstractAircraft enemyAircraft) {
        this.enemyAircraft = enemyAircraft;
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
    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    public List<BaseBullet> shoot() {
        return strategy.shoot(enemyAircraft);
    }
}
