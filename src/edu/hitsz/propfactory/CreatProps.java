package edu.hitsz.propfactory;

import edu.hitsz.prop.BoomProp;
import edu.hitsz.prop.FireProp;
import edu.hitsz.prop.HpProp;
import edu.hitsz.aircraft.AbstractAircraft;

/**
 * @author 谢岸峰
 */
public class CreatProps {

    public HpProp creatHpProp(AbstractAircraft enemyAircraft) {
        int x = enemyAircraft.getLocationX();
        int y = enemyAircraft.getLocationY();
        int speedX = 0;
        int speedY = enemyAircraft.getSpeedY();

        AbstractPropFactory hpPropFactory;
        AbstractPropProduct hpPropProduct;

        hpPropFactory = new CreatHpProp();
        hpPropProduct= hpPropFactory.creatPropProduct(x,y,speedX,speedY);
        return hpPropProduct.creatHpProp(x,y,speedX,speedY);
    }

    public FireProp creatFireProp(AbstractAircraft enemyAircraft) {
        int x = enemyAircraft.getLocationX();
        int y = enemyAircraft.getLocationY();
        int speedX = 0;
        int speedY = enemyAircraft.getSpeedY();

        AbstractPropFactory firePropFactory;
        AbstractPropProduct firePropProduct;

        firePropFactory = new CreatFireProp();
        firePropProduct= firePropFactory.creatPropProduct(x,y,speedX,speedY);
        return firePropProduct.creatFireProp(x,y,speedX,speedY);
    }

    public BoomProp creatBoomProp(AbstractAircraft enemyAircraft) {
        int x = enemyAircraft.getLocationX();
        int y = enemyAircraft.getLocationY();
        int speedX = 0;
        int speedY = enemyAircraft.getSpeedY();

        AbstractPropFactory boomPropFactory;
        AbstractPropProduct boomPropProduct;

        boomPropFactory = new CreatBoomProp();
        boomPropProduct= boomPropFactory.creatPropProduct(x,y,speedX,speedY);
        return boomPropProduct.creatBoomProp(x,y,speedX,speedY);
    }
}
