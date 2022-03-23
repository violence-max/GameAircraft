package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.MobEnemy;

public interface EnemyAircraftProduct {
    EliteEnemy createliteenemy();
    MobEnemy creatmobenemy();
    BossEnemy creatbossenemy();
}
