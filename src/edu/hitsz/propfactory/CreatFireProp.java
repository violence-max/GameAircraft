package edu.hitsz.propfactory;

public class CreatFireProp extends PropFactory {

    @Override
    public PropProduct creatPropProduct(int locationX, int locationY, int speedX, int speedY) {
        return new FirePropProduct(locationX,locationY,speedX,speedY);
    }
}
