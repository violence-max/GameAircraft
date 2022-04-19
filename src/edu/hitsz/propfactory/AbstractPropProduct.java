package edu.hitsz.propfactory;

import edu.hitsz.Prop.BoomProp;
import edu.hitsz.Prop.FireProp;
import edu.hitsz.Prop.HpProp;

public abstract class AbstractPropProduct {
    public HpProp creathpprop(int locationX, int locationY, int speedX, int speedY){
        return null;
    }

    public FireProp creatfireprop(int locationX, int locationY, int speedX, int speedY){
        return null;
    }

    public BoomProp creatboomprop(int locationX, int locationY, int speedX, int speedY){
        return null;
    }
}
