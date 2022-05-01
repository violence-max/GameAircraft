package edu.hitsz.propfactory;

import edu.hitsz.prop.FireProp;

/**
 * @author 谢岸峰
 */
public class FirePropProduct extends AbstractPropProduct {
    public FirePropProduct(int locationX, int locationY, int speedX, int speedY){

    }

    @Override
    public FireProp creatFireProp(int locationX, int locationY, int speedX, int speedY) {
        return new FireProp(locationX,locationY,speedX,speedY);
    }
}
