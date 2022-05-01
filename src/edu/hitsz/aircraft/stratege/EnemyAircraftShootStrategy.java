package edu.hitsz.aircraft.stratege;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

/**
 * @author 谢岸峰
 */
public interface EnemyAircraftShootStrategy {
    /**
     * 返回带有相应抽象飞机对象位置和速度参数的敌机子弹列表
     * @param abstractAircraft
     * @return
     */
    public List<BaseBullet> shoot(AbstractAircraft abstractAircraft);
}
