package edu.hitsz.Prop;

public class CreatBoomProp extends PropFactory{
    @Override
    public PropProduct creatPropProduct(int locationX, int locationY, int speedX, int speedY) {
        return new BoomPropProduct(locationX,locationY,speedX,speedY);
    }
}
