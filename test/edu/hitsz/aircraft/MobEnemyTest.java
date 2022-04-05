package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.enemyfactory.CreatMobeEnemy;
import edu.hitsz.enemyfactory.EnemyAircraftFactory;
import edu.hitsz.enemyfactory.EnemyAircraftProduct;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MobEnemyTest {

    private MobEnemy mobenemy;
    private List<BaseBullet> enemyBullets = new LinkedList<>();

    @BeforeEach
    void setUp() {
        System.out.println("**--Excuted before each test method in this class--**");
        EnemyAircraftFactory mobenemyfactory;
        EnemyAircraftProduct mobenemyproduct;

        mobenemyfactory = new CreatMobeEnemy();
        mobenemyproduct = mobenemyfactory.creatEnemyProduct();
        mobenemy = mobenemyproduct.creatmobenemy();
    }

    @AfterEach
    void tearDown() {
        System.out.println("**--Excuted after each test method in this class--**");
        mobenemy = null;
        enemyBullets =null;
    }

    @DisplayName("Test shoot method")
    @Test
    void shoot() {
        enemyBullets.addAll(mobenemy.shoot());
        assertNotNull(enemyBullets);
    }
}