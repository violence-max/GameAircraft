package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.MobEnemy;

public interface EnemyAircraftProduct {
    EliteEnemy createEliteEnemy();
    MobEnemy creatMobEnemy();
    BossEnemy creatBossEnemy();
}
