package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class CreatEliteEnemy implements EnemyAircraftFactory{

    @Override
    public EnemyAircraftProduct creatEnemyProduct(){
        return new EliteEnemyProduct();
    }
}
