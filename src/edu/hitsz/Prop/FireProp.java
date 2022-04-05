package edu.hitsz.Prop;

public class FireProp extends AbstractProp {

    public FireProp(int locationX, int locationY, int speedX, int speedY){
        super(locationX,locationY,speedX,speedY);
    }

    public void fire(){
        System.out.println("FIreSuply active!");
    }

}
