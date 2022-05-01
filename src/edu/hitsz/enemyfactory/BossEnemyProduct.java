package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

/**
 * @author 谢岸峰
 */
public class BossEnemyProduct implements EnemyAircraftProduct {

    public static int bossEnemyHp = 600;
    @Override
    public EliteEnemy createEliteEnemy() {
        return null;
    }

    @Override
    public BossEnemy creatBossEnemy() {
        return new BossEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2),
                2,
                0,
                bossEnemyHp
        );
    }

    @Override
    public MobEnemy creatMobEnemy() {
        return null;
    }
}

