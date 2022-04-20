package AircraftStrategy;

import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public class Context {
    private EnemyAircraftActionStrategy strategy;
    public void setStrategy(EnemyAircraftActionStrategy strategy){
        this.strategy = strategy;
    }

    public List<BaseBullet> excuteShootAction(){
        return strategy.shoot();
    }
}
