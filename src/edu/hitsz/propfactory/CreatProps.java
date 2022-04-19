package edu.hitsz.propfactory;

import edu.hitsz.Prop.BoomProp;
import edu.hitsz.Prop.FireProp;
import edu.hitsz.Prop.HpProp;
import edu.hitsz.aircraft.AbstractAircraft;

public class CreatProps {

    public HpProp creathpprop(AbstractAircraft enemyaircraft) {
        int x = enemyaircraft.getLocationX();
        int y = enemyaircraft.getLocationY();
        int speedX = 0;
        int speedY = enemyaircraft.getSpeedY();

        AbstractPropFactory hppropfactory;
        AbstractPropProduct hppropproduct;

        hppropfactory = new CreatHpProp();
        hppropproduct= hppropfactory.creatPropProduct(x,y,speedX,speedY);
        return hppropproduct.creathpprop(x,y,speedX,speedY);
    }

    public FireProp creatfireprop(AbstractAircraft enemyaircraft) {
        int x = enemyaircraft.getLocationX();
        int y = enemyaircraft.getLocationY();
        int speedX = 0;
        int speedY = enemyaircraft.getSpeedY();

        AbstractPropFactory firepropfactory;
        AbstractPropProduct firepropproduct;

        firepropfactory = new CreatFireProp();
        firepropproduct= firepropfactory.creatPropProduct(x,y,speedX,speedY);
        return firepropproduct.creatfireprop(x,y,speedX,speedY);
    }

    public BoomProp creatboomprop(AbstractAircraft enemyaircraft) {
        int x = enemyaircraft.getLocationX();
        int y = enemyaircraft.getLocationY();
        int speedX = 0;
        int speedY = enemyaircraft.getSpeedY();

        AbstractPropFactory boompropfactory;
        AbstractPropProduct boompropproduct;

        boompropfactory = new CreatBoomProp();
        boompropproduct= boompropfactory.creatPropProduct(x,y,speedX,speedY);
        return boompropproduct.creatboomprop(x,y,speedX,speedY);
    }
}
