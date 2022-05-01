package edu.hitsz.aircraft.stratege;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 谢岸峰
 */
public class BossEnemyShoot implements EnemyAircraftShootStrategy {


    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    @Override
    public List<BaseBullet> shoot(AbstractAircraft bossEnemy) {
        List<BaseBullet> res = new LinkedList<>();
        int x = bossEnemy.getLocationX();
        /**
         * 子弹射击方向 (向上发射：-1，向下发射：1)
         */
        int eLiteDirection = 1;
        int y = bossEnemy.getLocationY() + eLiteDirection *2;
        int speedY = bossEnemy.getSpeedY() + eLiteDirection *5;
        int speddX;
        BaseBullet basebullet;
        /**
         * 子弹一次发射数量
         */
        int eliteShootNum = 4;
        for(int i = 0; i< eliteShootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散

            /**
             * 子弹横向分散发射,将子弹自中间分开.左右两边的子弹具有相反的横向速度
             */
            /**
             * 子弹伤害
             */
            int elitePower = 50;
            if (i <= (eliteShootNum /2-1)){
                speddX = bossEnemy.getSpeedX()-6+3*i;
            }
            else{
                speddX = bossEnemy.getSpeedX()+3*(i-2)+3;
            }
            basebullet = new EnemyBullet(x + (i*2 - eliteShootNum + 1)*10, y, speddX, speedY, elitePower);
            res.add(basebullet);
        }
        return res;
    }
}
