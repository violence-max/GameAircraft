package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class EliteEnemyProduct implements EnemyAircraftProduct {
    @Override
    public MobEnemy creatmobenemy() {
        return null;
    }

    @Override
    public BossEnemy creatbossenemy() {
        return null;
    }

    @Override
    public EliteEnemy createliteenemy() {
        return new EliteEnemy(
                (int) ( Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2)*1,
                0,
                10,
                60);
    }
}
