package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class MobEnemyProduct implements EnemyAircraftProduct {

    public static int mobEnemyHp = 30;
    public static int mobEnemySpeedY = 10;

    @Override
    public EliteEnemy createEliteEnemy() {
        return null;
    }

    @Override
    public BossEnemy creatBossEnemy() {
        return null;
    }

    @Override
    public MobEnemy creatMobEnemy() {
        return new MobEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2),
                0,
                mobEnemySpeedY,
                mobEnemyHp);
    }
}
