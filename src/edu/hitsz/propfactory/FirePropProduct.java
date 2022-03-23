package edu.hitsz.propfactory;

import edu.hitsz.Prop.FireProp;

public class FirePropProduct extends PropProduct {
    public FirePropProduct(int locationX, int locationY, int speedX, int speedY){

    }

    @Override
    public FireProp creatfireprop(int locationX, int locationY, int speedX, int speedY) {
        return new FireProp(locationX,locationY,speedX,speedY);
    }
}
