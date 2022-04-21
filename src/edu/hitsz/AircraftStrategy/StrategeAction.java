package edu.hitsz.AircraftStrategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public class StrategeAction {
    private Context strategy = new Context();
    private EliteEnemyAction eliteEnemyAction = new EliteEnemyAction();
    private BossEnemyAction bossEnemyAction = new BossEnemyAction();
    private MobEnemyAction mobEnemyAction = new MobEnemyAction();
    private HeroAircraftAction heroAircraftAction = new HeroAircraftAction();

    public List<BaseBullet> EliteEnemyShootStrategy(AbstractAircraft aircraft){
        eliteEnemyAction.setEliteEnemy(aircraft);
        strategy.setEnemyStrategy(eliteEnemyAction);
        return strategy.excuteEnemyShootAction();
    }

    public List<BaseBullet> BossEnemyShootStrategy(AbstractAircraft aircraft){
        bossEnemyAction.setBossEnemy(aircraft);
        strategy.setEnemyStrategy(bossEnemyAction);
        return strategy.excuteEnemyShootAction();
    }

    public List<BaseBullet> MobEnemyShootStrategy(AbstractAircraft aircraft){
        mobEnemyAction.setMobEnemy(aircraft);
        strategy.setEnemyStrategy(mobEnemyAction);
        return strategy.excuteEnemyShootAction();
    }

    public List<BaseBullet> HeroAircraftStrategyDierectely(HeroAircraft heroAircraft){
        heroAircraftAction.setHeroAircraft(heroAircraft);
        strategy.setHerostrategy(heroAircraftAction);
        return strategy.excuteHeroShootActionDirectely();
    }

    public List<BaseBullet> HeroAircraftStrategyScattering(HeroAircraft heroAircraft){
        heroAircraftAction.setHeroAircraft(heroAircraft);
        strategy.setHerostrategy(heroAircraftAction);
        return strategy.excuteHeroShootActionScattering();
    }

}
