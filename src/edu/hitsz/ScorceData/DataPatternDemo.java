package edu.hitsz.ScorceData;

public class DataPatternDemo {

    private int score;
    private int month;
    private int day;
    private int hour;
    private int minute;

    private DataDao dateDao = new DataDaoimpi();

    public void setScore(int score) {
        this.score = score;
    }

    public void setMonth(){
        month = dateDao.getMonth();
    }

    public void setDay(){
        day = dateDao.getDay();
    }

    public void setHour(){
        hour = dateDao.getHour();
    }

    public void setMinute(){
        minute = dateDao.getMinute();
    }

    public void addData(){
        dateDao.doAdd(score,month,day,hour,minute);
    }

    public void sort(){
        dateDao.sortData();
    }

    public void fileCreat(){
        dateDao.creatFile();
    }

    public void fileWrite(){
        dateDao.fileWriter();
    }

    public void fileRead(){
        dateDao.fileReader();
    }

    public void fileCopy(){
        dateDao.fileCopy();
    }

}
