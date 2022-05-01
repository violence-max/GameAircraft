package edu.hitsz.aircraft.stratege;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

/**
 * @author 谢岸峰
 */
public interface HeroAircraftShootStrategy {
    /**
     * 返回带有英雄机位置和速度参数的英雄子弹列表
     * @param abstractAircraft
     * @return
     */
    public List<BaseBullet> shoot(AbstractAircraft abstractAircraft);
}
