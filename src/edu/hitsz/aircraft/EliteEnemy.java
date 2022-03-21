package edu.hitsz.aircraft;

import edu.hitsz.application.Game;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

/**
 * 精英敌机
 * 
 */
public class EliteEnemy extends AbstractAircraft{

    /** 攻击方式 */
    private int elite_shootNum = 1;     //子弹一次发射数量
    private int elite_power = 50;       //子弹伤害
    private int elite_direction = 1;  //子弹射击方向 (向上发射：-1，向下发射：1)

    public EliteEnemy(int locationX,int locationY,int speedX,int speedY,int hp){
        super(locationX,locationY,speedX,speedY,hp);
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

    @Override
    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    public List<BaseBullet> shoot() {
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + elite_direction*2;
        int speedX = 0;
        int speedY = this.getSpeedY() + elite_direction*5;
        BaseBullet basebullet;
        for(int i=0; i<elite_shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            basebullet = new EnemyBullet(x + (i*2 - elite_shootNum + 1)*10, y, speedX, speedY, elite_power);
            res.add(basebullet);
        }
        return res;
    }
}
