package edu.hitsz.propfactory;

/**
 * @author 谢岸峰
 */
public abstract class AbstractPropFactory {
    /**
     * 返回一个带有位置和移动速度参数的抽象产品类
     * @param locationX
     * @param locationY
     * @param speedX
     * @param speedY
     * @return 一个抽象产品类
     */
    public abstract AbstractPropProduct creatPropProduct(int locationX, int locationY, int speedX, int speedY);
}
