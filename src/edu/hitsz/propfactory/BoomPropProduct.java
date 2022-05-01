package edu.hitsz.propfactory;

import edu.hitsz.prop.BoomProp;

/**
 * @author 谢岸峰
 */
public class BoomPropProduct extends AbstractPropProduct {
    public BoomPropProduct(int locationX, int locationY, int speedX, int speedY){

    }

    @Override
    public BoomProp creatBoomProp(int locationX, int locationY, int speedX, int speedY) {
        return new BoomProp(locationX,locationY,speedX,speedY);
    }
}
