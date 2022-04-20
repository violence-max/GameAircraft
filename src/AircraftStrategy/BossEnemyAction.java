package AircraftStrategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;

import java.util.LinkedList;
import java.util.List;

public class BossEnemyAction implements EnemyAircraftActionStrategy{
    /** 攻击方式 */
    private int eliteShootNum = 4;     //子弹一次发射数量
    private int elitePower = 50;       //子弹伤害
    private int eLiteDirection = 1;  //子弹射击方向 (向上发射：-1，向下发射：1)

    private AbstractAircraft bossEnemy;
    public void setBossEnemy(AbstractAircraft bossenemy){
        this.bossEnemy = bossenemy;
    }

    @Override
    public List<BaseBullet> shoot() {
        List<BaseBullet> res = new LinkedList<>();
        int x = bossEnemy.getLocationX();
        int y = bossEnemy.getLocationY() + eLiteDirection*2;
        int speedY = bossEnemy.getSpeedY() + eLiteDirection*5;
        int speddX;
        BaseBullet basebullet;
        for(int i=0; i<eliteShootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散

            /**
             * 子弹横向分散发射,将子弹自中间分开.左右两边的子弹具有相反的横向速度
             */
            if (i <= (eliteShootNum/2-1)){
                speddX = bossEnemy.getSpeedX()-6+3*i;
                basebullet = new EnemyBullet(x + (i*2 - eliteShootNum + 1)*10, y, speddX, speedY, elitePower);
            }
            else{
                speddX = bossEnemy.getSpeedX()+3*(i-2)+3;
                basebullet = new EnemyBullet(x + (i*2 - eliteShootNum + 1)*10, y, speddX, speedY, elitePower);
            }
            res.add(basebullet);
        }
        return res;
    }
}
