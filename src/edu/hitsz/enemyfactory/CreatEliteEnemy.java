package edu.hitsz.enemyfactory;

public class CreatEliteEnemy implements EnemyAircraftFactory {

    @Override
    public EnemyAircraftProduct creatEnemyProduct(){
        return new EliteEnemyProduct();
    }
}
