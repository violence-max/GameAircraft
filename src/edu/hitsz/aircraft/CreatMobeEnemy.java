package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class CreatMobeEnemy implements EnemyAircraftFactory {
    @Override
    public EnemyAircraftProduct creatEnemyProduct() {
        return new MobEnemyProduct();
    }
}