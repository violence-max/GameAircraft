package edu.hitsz.Prop;

public class HpProp extends AbstractProp{

    private int hp = -50;

    public HpProp(int locationX,int locationY,int speedX,int speedY){
        super(locationX,locationY,speedX,speedY);
    }

    public int increasehp(){
        return hp;
    }

}
