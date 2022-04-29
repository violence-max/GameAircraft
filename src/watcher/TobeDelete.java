package watcher;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

/**
 * @author 谢岸峰
 */
public interface TobeDelete {
    /**
     * 更新方法
     * @param enemyAircrafts
     * @param enemyBullets
     */
    void update(List<AbstractAircraft> enemyAircrafts,List<BaseBullet> enemyBullets);
}
