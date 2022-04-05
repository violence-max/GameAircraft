package edu.hitsz.propfactory;

public class CreatFireProp extends AbstractPropFactory {

    @Override
    public AbstractPropProduct creatPropProduct(int locationX, int locationY, int speedX, int speedY) {
        return new FirePropProduct(locationX,locationY,speedX,speedY);
    }
}
