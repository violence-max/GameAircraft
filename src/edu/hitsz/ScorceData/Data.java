package edu.hitsz.ScorceData;

public class Data {
    private int dataId;
    private String userName = "violence";
    private int score;
    private int month;
    private int day;
    private int hour;
    private int minute;

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

    public int getDataId() {
        return dataId;
    }

    public int getScore() {
        return score;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String getUserName() {
        return userName;
    }
}
