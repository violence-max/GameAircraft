package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class BossEnemyProduct implements EnemyAircraftProduct {
    @Override
    public EliteEnemy createliteenemy() {
        return null;
    }

    @Override
    public BossEnemy creatbossenemy() {
        return new BossEnemy(
                (int) ( Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2)*1,
                2,
                0,
                3000
        );
    }

    @Override
    public MobEnemy creatmobenemy() {
        return null;
    }
}

