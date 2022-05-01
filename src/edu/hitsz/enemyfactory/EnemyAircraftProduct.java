package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.MobEnemy;

/**
 * @author 谢岸峰
 */
public interface EnemyAircraftProduct {
    /**
     * 创建具体精英敌机
     * @return
     */
    EliteEnemy createEliteEnemy();

    /**
     * 创建具体普通敌机
     * @return
     */
    MobEnemy creatMobEnemy();

    /**
     * 创建具体boss敌机
     * @return
     */
    BossEnemy creatBossEnemy();
}
