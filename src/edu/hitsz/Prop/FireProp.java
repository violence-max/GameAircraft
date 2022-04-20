package edu.hitsz.Prop;

import AircraftStrategy.StrategeAction;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public class FireProp extends AbstractProp {

    public FireProp(int locationX, int locationY, int speedX, int speedY){
        super(locationX,locationY,speedX,speedY);
    }

    public void fire(HeroAircraft heroAircraft, List<BaseBullet> heroBullets){
        StrategeAction heroStrategy = new StrategeAction();
        heroBullets.addAll(heroStrategy.HeroAircraftStrategyScattering(heroAircraft));
    }
}
