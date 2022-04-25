package edu.hitsz.AircraftStrategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public interface EnemyAircraftShootStrategy {
    public List<BaseBullet> shoot(AbstractAircraft abstractAircraft);
}
