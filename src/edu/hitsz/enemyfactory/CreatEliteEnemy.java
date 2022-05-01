package edu.hitsz.enemyfactory;

/**
 * @author 谢岸峰
 */
public class CreatEliteEnemy implements EnemyAircraftFactory {

    @Override
    public EnemyAircraftProduct creatEnemyProduct(){
        return new EliteEnemyProduct();
    }
}
