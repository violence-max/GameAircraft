package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class CreatEliteEnemy implements EnemyAircraftFactory{

    @Override
    public EnemyAircraftProduct creatEnemyProduct(){
        return new EliteEnemy(
                (int) ( Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2)*1,
                0,
                10,
                60);
    }
}
