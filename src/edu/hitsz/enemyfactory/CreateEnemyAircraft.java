package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.MobEnemy;

/**
 * @author 谢岸峰
 */
public class CreateEnemyAircraft {

        public EliteEnemy createEliteEnemy(){
            EnemyAircraftFactory eliteEnemyFactory;
            EnemyAircraftProduct eliteEnemyProduct;

            eliteEnemyFactory = new CreatEliteEnemy();
            eliteEnemyProduct = eliteEnemyFactory.creatEnemyProduct();
            return eliteEnemyProduct.createEliteEnemy();

        }

        public MobEnemy creatMobEnemy(){
            EnemyAircraftFactory mobEnemyFactory;
            EnemyAircraftProduct mobenemyproduct;

            mobEnemyFactory = new CreatMobeEnemy();
            mobenemyproduct = mobEnemyFactory.creatEnemyProduct();
            return mobenemyproduct.creatMobEnemy();
        }

        public BossEnemy creatBossEnemy(){
            EnemyAircraftFactory bossEnemyFactory;
            EnemyAircraftProduct bossEnemyProduct;

            bossEnemyFactory = new CreatBossEnemy();
            bossEnemyProduct = bossEnemyFactory.creatEnemyProduct();
            return bossEnemyProduct.creatBossEnemy();
        }
}
