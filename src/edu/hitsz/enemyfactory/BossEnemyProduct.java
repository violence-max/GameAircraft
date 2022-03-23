package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.MobEnemy;

public class BossEnemyProduct implements EnemyAircraftProduct {
    @Override
    public EliteEnemy createliteenemy() {
        return null;
    }

    @Override
    public BossEnemy creatbossenemy() {
        return null;
    }

    @Override
    public MobEnemy creatmobenemy() {
        return null;
    }
}

