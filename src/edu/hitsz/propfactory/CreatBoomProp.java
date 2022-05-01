package edu.hitsz.propfactory;

/**
 * @author 谢岸峰
 */
public class CreatBoomProp extends AbstractPropFactory {
    @Override
    public AbstractPropProduct creatPropProduct(int locationX, int locationY, int speedX, int speedY) {
        return new BoomPropProduct(locationX,locationY,speedX,speedY);
    }
}
