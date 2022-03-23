package edu.hitsz.Prop;

public abstract class PropProduct {
    public HpProp creathpprop(int locationX,int locationY,int speedX,int speedY){
        return new HpProp(locationX,locationY,speedX,speedY);
    }

    public FireProp creatfireprop(int locationX, int locationY, int speedX, int speedY){
        return new FireProp(locationX,locationY,speedX,speedY);
    }

    public BoomProp creatboomprop(int locationX,int locationY,int speedX,int speedY){
        return new BoomProp(locationX,locationY,speedX,speedY);
    }
}
