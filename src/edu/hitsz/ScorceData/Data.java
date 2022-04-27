package edu.hitsz.ScorceData;

import java.io.Serializable;

public class Data implements Serializable {
    private Integer dataId;
    private String userName;
    private final Integer score;
    private final Integer month;
    private final Integer day;
    private final Integer hour;
    private final Integer minute;

    public Data(int score,int month,int day,int hour,int minute){
        this.score = score;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public Integer getDataId() {
        return dataId;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getDay() {
        return day;
    }

    public Integer getHour() {
        return hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString(){
        return "第"+dataId+"名,"+userName+","+score+","+month+"月"+day+"日"+","+hour+"时"+minute+"分";
    }
}
