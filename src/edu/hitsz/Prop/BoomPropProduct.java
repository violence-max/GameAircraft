package edu.hitsz.Prop;

public class BoomPropProduct extends PropProduct{
    public BoomPropProduct(int locationX, int locationY, int speedX, int speedY){

    }

    @Override
    public BoomProp creatboomprop(int locationX, int locationY, int speedX, int speedY) {
        return new BoomProp(locationX,locationY,speedX,speedY);
    }
}
