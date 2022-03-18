package edu.hitsz.Prop;

public class BoomProp extends AbstractProp{
    public BoomProp(int locationX,int locationY,int speedX,int speedY){
        super(locationX,locationY,speedX,speedY);
    }

    public void boom(){
        System.out.println("BoomSuply active!");
    }
}
