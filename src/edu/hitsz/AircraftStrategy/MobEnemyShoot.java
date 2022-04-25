package edu.hitsz.AircraftStrategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.LinkedList;
import java.util.List;

public class MobEnemyShoot implements EnemyAircraftShootStrategy {
    @Override
    public List<BaseBullet> shoot(AbstractAircraft mobEnemy){
        return new LinkedList<>();
    }
}
