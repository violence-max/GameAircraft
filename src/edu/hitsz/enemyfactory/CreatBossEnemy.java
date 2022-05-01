package edu.hitsz.enemyfactory;

/**
 * @author 谢岸峰
 */
public class CreatBossEnemy implements EnemyAircraftFactory {
    @Override
    public EnemyAircraftProduct creatEnemyProduct() {
        return new BossEnemyProduct();
    }
}
