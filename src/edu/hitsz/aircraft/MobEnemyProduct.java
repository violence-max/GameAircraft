package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class MobEnemyProduct implements EnemyAircraftProduct{
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
        return new MobEnemy(
                (int) ( Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2)*1,
                0,
                10,
                30);
    }
}
