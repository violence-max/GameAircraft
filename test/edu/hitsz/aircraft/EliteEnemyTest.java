package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.enemyfactory.CreatEliteEnemy;
import edu.hitsz.enemyfactory.EnemyAircraftFactory;
import edu.hitsz.enemyfactory.EnemyAircraftProduct;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EliteEnemyTest {

    private EliteEnemy eliteenemy;
    private List<BaseBullet> enemyBullets= new LinkedList<>();;


    @BeforeEach
    void setUp() {
        System.out.println("**--Excuted before each test method in this class--**");
        EnemyAircraftFactory eliteenemyfactory;
        EnemyAircraftProduct eliteenemyproduct;

        eliteenemyfactory = new CreatEliteEnemy();
        eliteenemyproduct = eliteenemyfactory.creatEnemyProduct();
        eliteenemy = eliteenemyproduct.createliteenemy();
    }

    @AfterEach
    void tearDown() {
        System.out.println("**--Excuted after each test method in this class--**");
        eliteenemy = null;
        enemyBullets = null;
    }

    @DisplayName("Test shoot method")
    @Test
    void shoot() {
        enemyBullets.addAll(eliteenemy.shoot());
        assertNotNull(enemyBullets);
    }
}