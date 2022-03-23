package edu.hitsz.enemyfactory;

public class CreatMobeEnemy implements EnemyAircraftFactory {
    @Override
    public EnemyAircraftProduct creatEnemyProduct() {
        return new MobEnemyProduct();
    }
}