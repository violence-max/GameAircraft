package edu.hitsz.AircraftStrategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class HeroAircraftShootWithFire implements HeroAircraftShootStrategy{
    /** 攻击方式 */
    private int eliteShootNum = 1;     //子弹一次发射数量
    private int elitePower = 30;       //子弹伤害
    private int eLiteDirection = -1;  //子弹射击方向 (向上发射：-1，向下发射：1)

    @Override
    public List<BaseBullet> shoot(AbstractAircraft heroAircraft) {
        //子弹数量增加
        eliteShootNum = 4;

        //子弹伤害翻倍
        elitePower = 60;

        List<BaseBullet> res = new LinkedList<>();
        int x = heroAircraft.getLocationX();
        int y = heroAircraft.getLocationY() + eLiteDirection*2;
        int speedX;
        int speedY = heroAircraft.getSpeedY() + eLiteDirection*5;
        BaseBullet basebullet;
        for(int i=0; i<eliteShootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散

            /**
             * 子弹横向分散发射,将子弹自中间分开.左右两边的子弹具有相反的横向速度
             */
            if (i <= (eliteShootNum/2-1)){
                speedX = heroAircraft.getSpeedX()-4+2*i;
                basebullet = new HeroBullet(x + (i*2 - eliteShootNum + 1)*10, y, speedX, speedY, elitePower);
            }
            else{
                speedX = heroAircraft.getSpeedX()+2*(i-2)+2;
                basebullet = new HeroBullet(x + (i*2 - eliteShootNum + 1)*10, y, speedX, speedY, elitePower);
            }
            res.add(basebullet);
        }
        return res;
    }
}
