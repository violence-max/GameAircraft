package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HeroAircraftTest {

    private HeroAircraft heroAircraft;
    private List<BaseBullet> heroBullets = new LinkedList<>();

    @BeforeEach
    void setUp() {
        System.out.println("**--Excuted before each test method in this class--**");
        heroAircraft = HeroAircraft.getheroaircraft();
    }

    @AfterEach
    void tearDown() {
        System.out.println("**--Excuted after each test method in this class--**");
        heroAircraft = null;
        heroBullets = null;
    }

    @DisplayName("Test getheheroaircraft method")
    @Test
    void getheroaircraft() {
        System.out.println("Test getheroaircraft method Excuted");
        assertNotNull(heroAircraft);
        int x = heroAircraft.getLocationX();
        int y = heroAircraft.getLocationY();
        int speedY = heroAircraft.getSpeedY();
        assertNotNull(x);
        assertNotNull(y);
        assertNotNull(speedY);
    }

    @DisplayName("Test shoot method")
    @Test
    void shoot() {
        System.out.println("Test shoot method Excuted");
        heroBullets.addAll(heroAircraft.shoot());
        assertNotNull(heroBullets);
    }
}