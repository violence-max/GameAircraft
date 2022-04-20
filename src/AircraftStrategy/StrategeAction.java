package AircraftStrategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public class StrategeAction {
    private Context strategy = new Context();
    private EliteEnemyAction eliteEnemyAction = new EliteEnemyAction();
    private BossEnemyAction bossEnemyAction = new BossEnemyAction();
    private MobEnemyAction mobEnemyAction = new MobEnemyAction();

    public List<BaseBullet> EliteEnemyShootStrategy(AbstractAircraft aircraft){
        eliteEnemyAction.setEliteEnemy(aircraft);
        strategy.setStrategy(eliteEnemyAction);
        return strategy.excuteShootAction();
    }

    public List<BaseBullet> BossEnemyShootStrategy(AbstractAircraft aircraft){
        bossEnemyAction.setBossEnemy(aircraft);
        strategy.setStrategy(bossEnemyAction);
        return strategy.excuteShootAction();
    }

    public List<BaseBullet> MobEnemyShootStrategy(AbstractAircraft aircraft){
        mobEnemyAction.setMobEnemy(aircraft);
        strategy.setStrategy(mobEnemyAction);
        return strategy.excuteShootAction();
    }
}
