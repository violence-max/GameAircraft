package edu.hitsz.Prop;

import edu.hitsz.AircraftStrategy.HeroAircraftShootStrategy;
import edu.hitsz.AircraftStrategy.HeroAircraftShootWithFire;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public class FireProp extends AbstractProp {

    public FireProp(int locationX, int locationY, int speedX, int speedY){
        super(locationX,locationY,speedX,speedY);
    }
    private HeroAircraftShootStrategy heroAircraftShootWithFire = new HeroAircraftShootWithFire();

    public void fire(HeroAircraft heroAircraft){
        heroAircraft.setStrategy(heroAircraftShootWithFire);
    }


}
