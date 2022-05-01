package edu.hitsz.propfactory;

/**
 * @author 谢岸峰
 */
public class CreatHpProp extends AbstractPropFactory {
    @Override
    public AbstractPropProduct creatPropProduct(int locationX, int locationY, int speedX, int speedY) {
        return new HpPropProduct(locationX,locationY,speedX,speedY);
    }
}
