package edu.hitsz.propfactory;

import edu.hitsz.Prop.HpProp;

public class HpPropProduct extends PropProduct {

    public HpPropProduct(int locationX, int locationY, int speedX, int speedY){

    }

    @Override
    public HpProp creathpprop(int locationX, int locationY, int speedX, int speedY) {
        return new HpProp(locationX,locationY,speedX,speedY);
    }
}
