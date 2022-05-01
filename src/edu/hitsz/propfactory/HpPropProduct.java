package edu.hitsz.propfactory;

import edu.hitsz.prop.HpProp;

/**
 * @author 谢岸峰
 */
public class HpPropProduct extends AbstractPropProduct {

    public HpPropProduct(int locationX, int locationY, int speedX, int speedY){

    }

    @Override
    public HpProp creatHpProp(int locationX, int locationY, int speedX, int speedY) {
        return new HpProp(locationX,locationY,speedX,speedY);
    }
}
