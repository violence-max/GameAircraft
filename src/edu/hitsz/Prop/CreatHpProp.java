package edu.hitsz.Prop;

public class CreatHpProp extends PropFactory{
    @Override
    public PropProduct creatPropProduct(int locationX, int locationY, int speedX, int speedY) {
        return new HpPropProduct(locationX,locationY,speedX,speedY);
    }
}
