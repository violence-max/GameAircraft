package edu.hitsz.watcher;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

/**
 * @author 谢岸峰
 */
public interface EnemyObserver {
    /**
     * 更新方法
     * @param enemyAircraft
     * @param enemyBullets
     */
    void update(List<AbstractAircraft> enemyAircraft,List<BaseBullet> enemyBullets);
}
