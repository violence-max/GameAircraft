package edu.hitsz.prop;

/**
 * @author 谢岸峰
 */
public class HpProp extends AbstractProp {

    private int hp = -50;

    public HpProp(int locationX,int locationY,int speedX,int speedY){
        super(locationX,locationY,speedX,speedY);
    }

    public int increaseHp(){
        return hp;
    }

}
