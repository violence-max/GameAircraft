package AircraftStrategy;

import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public class Context {
    private EnemyAircraftActionStrategy enemystrategy;
    private HeroAircraftActionStrategy herostrategy;
    public void setEnemyStrategy(EnemyAircraftActionStrategy strategy){
        this.enemystrategy = strategy;
    }
    public void setHerostrategy(HeroAircraftActionStrategy herostrategy){this.herostrategy = herostrategy;}

    public List<BaseBullet> excuteEnemyShootAction(){
        return enemystrategy.shoot();
    }

    public List<BaseBullet> excuteHeroShootActionDirectely(){
        return herostrategy.shootDirrectely();
    }

    public List<BaseBullet> excuteHeroShootActionScattering(){
        return herostrategy.shootScattring();
    }
}
