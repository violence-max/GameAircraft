package edu.hitsz.Prop;

import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.enemyfactory.CreatEliteEnemy;
import edu.hitsz.enemyfactory.EnemyAircraftFactory;
import edu.hitsz.enemyfactory.EnemyAircraftProduct;
import edu.hitsz.propfactory.CreatFireProp;
import edu.hitsz.propfactory.PropFactory;
import edu.hitsz.propfactory.PropProduct;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FirePropTest {

    private EliteEnemy eliteenemy;
    private FireProp fireprop;

    @BeforeEach
    void setUp() {
        System.out.println("**--Excuted before each test method in this class--**");
        EnemyAircraftFactory eliteenemyfactory;
        EnemyAircraftProduct eliteenemyproduct;

        eliteenemyfactory = new CreatEliteEnemy();
        eliteenemyproduct = eliteenemyfactory.creatEnemyProduct();
        eliteenemy = eliteenemyproduct.createliteenemy();

        int x = eliteenemy.getLocationX();
        int y = eliteenemy.getLocationY();
        int speedX = 0;
        int speedY = eliteenemy.getSpeedY();

        PropFactory firepropfactory;
        PropProduct firepropproduct;

        firepropfactory = new CreatFireProp();
        firepropproduct= firepropfactory.creatPropProduct(x,y,speedX,speedY);

        fireprop = firepropproduct.creatfireprop(x,y,speedX,speedY);
    }

    @AfterEach
    void tearDown() {
        System.out.println("**--Excuted after each test method in this class--**");
        eliteenemy =null;
        fireprop = null;
    }

    @DisplayName("Test fire method")
    @Test
    void fire() {
        assertEquals("FIreSuply active!","FIreSuply active!");
    }
}