package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

/**
 * @author 谢岸峰
 */
public class EliteEnemyProduct implements EnemyAircraftProduct {

    public static int eliteEnemyHp = 60;
    public static int eliteSpeedY = 10;

    @Override
    public MobEnemy creatMobEnemy() {
        return null;
    }

    @Override
    public BossEnemy creatBossEnemy() {
        return null;
    }

    @Override
    public EliteEnemy createEliteEnemy() {
        return new EliteEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2),
                0,
                eliteSpeedY,
                eliteEnemyHp);
    }
}
