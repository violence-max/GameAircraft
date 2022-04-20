package edu.hitsz.enemyfactory;

public class CreatBossEnemy implements EnemyAircraftFactory {
    @Override
    public EnemyAircraftProduct creatEnemyProduct() {
        return new BossEnemyProduct();
    }
}
