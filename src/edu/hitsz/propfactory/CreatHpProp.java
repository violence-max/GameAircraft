package edu.hitsz.propfactory;

public class CreatHpProp extends AbstractPropFactory {
    @Override
    public AbstractPropProduct creatPropProduct(int locationX, int locationY, int speedX, int speedY) {
        return new HpPropProduct(locationX,locationY,speedX,speedY);
    }
}
