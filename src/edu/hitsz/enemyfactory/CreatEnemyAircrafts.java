package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.MobEnemy;

public class CreatEnemyAircrafts {

        public EliteEnemy createliteenemy(){
            EnemyAircraftFactory eliteenemyfactory;
            EnemyAircraftProduct eliteenemyproduct;

            eliteenemyfactory = new CreatEliteEnemy();
            eliteenemyproduct = eliteenemyfactory.creatEnemyProduct();
            return eliteenemyproduct.createliteenemy();

        }

        public MobEnemy creatmobenemy(){
            EnemyAircraftFactory mobenemyfactory;
            EnemyAircraftProduct mobenemyproduct;

            mobenemyfactory = new CreatMobeEnemy();
            mobenemyproduct = mobenemyfactory.creatEnemyProduct();
            return mobenemyproduct.creatmobenemy();
        }
}
