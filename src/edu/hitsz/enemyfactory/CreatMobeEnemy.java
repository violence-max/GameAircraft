package edu.hitsz.enemyfactory;

/**
 * @author 谢岸峰
 */
public class CreatMobeEnemy implements EnemyAircraftFactory {
    @Override
    public EnemyAircraftProduct creatEnemyProduct() {
        return new MobEnemyProduct();
    }
}