package edu.hitsz.AircraftStrategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.LinkedList;
import java.util.List;

public class MobEnemyAction implements EnemyAircraftActionStrategy{
    private AbstractAircraft mobEnemy;

    public void setMobEnemy(AbstractAircraft mobEnemy) {
        this.mobEnemy = mobEnemy;
    }

    @Override
    public List<BaseBullet> shoot(){
        return new LinkedList<>();
    }
}
