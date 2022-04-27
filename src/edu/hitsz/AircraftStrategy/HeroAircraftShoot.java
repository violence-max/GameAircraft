package edu.hitsz.AircraftStrategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class HeroAircraftShoot implements HeroAircraftShootStrategy {
    /** 攻击方式 */
    private int eliteShootNum = 1;     //子弹一次发射数量
    private int elitePower = 30;       //子弹伤害
    private int eLiteDirection = -1;  //子弹射击方向 (向上发射：-1，向下发射：1)


    /**
     * 产生直射子弹
     * @return 射击出的子弹List
     */
    @Override
    public List<BaseBullet> shoot(AbstractAircraft heroAircraft) {
        List<BaseBullet> res = new LinkedList<>();
        int x = heroAircraft.getLocationX();
        int y = heroAircraft.getLocationY() + eLiteDirection*2;
        int speedX = 0;
        int speedY = heroAircraft.getSpeedY() + eLiteDirection*5;
        BaseBullet basebullet;
        for(int i=0; i<eliteShootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            basebullet = new HeroBullet(x + (i*2 - eliteShootNum + 1)*10, y, speedX, speedY, elitePower);
            res.add(basebullet);
        }
        return res;
    }

}