package edu.hitsz.Prop;

import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.enemyfactory.CreatEliteEnemy;
import edu.hitsz.enemyfactory.EnemyAircraftFactory;
import edu.hitsz.enemyfactory.EnemyAircraftProduct;
import edu.hitsz.propfactory.CreatHpProp;
import edu.hitsz.propfactory.AbstractPropFactory;
import edu.hitsz.propfactory.AbstractPropProduct;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoomPropTest {

    private EliteEnemy eliteenemy;
    private BoomProp boomprop;


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

        AbstractPropFactory boompropfactory;
        AbstractPropProduct boompropproduct;

        boompropfactory = new CreatHpProp();
        boompropproduct= boompropfactory.creatPropProduct(x,y,speedX,speedY);

        boomprop = boompropproduct.creatboomprop(x,y,speedX,speedY);
    }

    @AfterEach
    void tearDown() {
        System.out.println("**--Excuted after each test method in this class--**");
        eliteenemy = null;
        boomprop = null;
    }

    @DisplayName("Test boom method")
    @Test
    void boom() {
        assertEquals("BoomSuply active!","BoomSuply active!");
    }
}