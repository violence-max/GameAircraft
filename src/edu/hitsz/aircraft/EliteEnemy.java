package edu.hitsz.aircraft;

import edu.hitsz.aircraft.stratege.EliteEnemyShoot;
import edu.hitsz.aircraft.stratege.EnemyAircraftShootStrategy;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

/**
 * 精英敌机
 *
 * @author 谢岸峰
 */
public class EliteEnemy extends AbstractAircraft{




    public EliteEnemy(int locationX,int locationY,int speedX,int speedY,int hp){
        super(locationX,locationY,speedX,speedY,hp);
    }

    private final EnemyAircraftShootStrategy eliteEnemyShootStrategy = new EliteEnemyShoot();
    private final EnemyAircraftShootStrategy strategy = eliteEnemyShootStrategy;
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
