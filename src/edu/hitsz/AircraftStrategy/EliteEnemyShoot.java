package edu.hitsz.AircraftStrategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;

import java.util.LinkedList;
import java.util.List;

public class EliteEnemyShoot implements EnemyAircraftShootStrategy {

    /** 攻击方式 */
    private int eliteShootNum = 1;     //子弹一次发射数量
    private int elitePower = 50;       //子弹伤害
    private int eLiteDirection = 1;  //子弹射击方向 (向上发射：-1，向下发射：1)


    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    @Override
    public List<BaseBullet> shoot(AbstractAircraft eliteEnemy) {
        List<BaseBullet> res = new LinkedList<>();
        int x = eliteEnemy.getLocationX();
        int y = eliteEnemy.getLocationY() + eLiteDirection*2;
        int speedX = 0;
        int speedY = eliteEnemy.getSpeedY() + eLiteDirection*5;
        BaseBullet basebullet;
        for(int i=0; i<eliteShootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            basebullet = new EnemyBullet(x + (i*2 - eliteShootNum + 1)*10, y, speedX, speedY, elitePower);
            res.add(basebullet);
        }
        return res;
    }
}