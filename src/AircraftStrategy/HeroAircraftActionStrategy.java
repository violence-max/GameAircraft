package AircraftStrategy;

import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public interface HeroAircraftActionStrategy {
    public List<BaseBullet> shootDirrectely();
    public List<BaseBullet> shootScattring();
}
