package edu.hitsz.propfactory;

import edu.hitsz.prop.BoomProp;
import edu.hitsz.prop.FireProp;
import edu.hitsz.prop.HpProp;

/**
 * @author 谢岸峰
 */
public abstract class AbstractPropProduct {
    public HpProp creatHpProp(int locationX, int locationY, int speedX, int speedY){
        return null;
    }

    public FireProp creatFireProp(int locationX, int locationY, int speedX, int speedY){
        return null;
    }

    public BoomProp creatBoomProp(int locationX, int locationY, int speedX, int speedY){
        return null;
    }
}
