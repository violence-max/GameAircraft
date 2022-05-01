package edu.hitsz.source.data;

import java.util.LinkedList;

/**
 * @author 谢岸峰
 */
public class DataPatternDemo {

    private int score;
    private int month;
    private int day;
    private int hour;
    private int minute;

    private final DataDao dataDao = new DataDaoimpi();

    public void setScore(int score) {
        this.score = score;
    }

    public void setMonth(){
        month = dataDao.getMonth();
    }

    public void setDay(){
        day = dataDao.getDay();
    }

    public void setHour(){
        hour = dataDao.getHour();
    }

    public void setMinute(){
        minute = dataDao.getMinute();
    }

    public int getScore(){
        return score;
    }

    public void addData(){
        dataDao.doAdd(score,month,day,hour,minute);
    }

    public void sort(){
        dataDao.sortData();
    }

    public void fileCreat(){
        dataDao.creatFile();
    }

    public void fileWriter(){
        dataDao.fileWriter();}

    public void fileReader(){
        dataDao.fileReader();
    }

    public LinkedList<Data> getDataTable(){
        return dataDao.getDataTable();
    }

    public void setUserName(String userName){
        dataDao.setUserName(userName);
    }

    public void removeByRank(int rank){
        dataDao.removeByRank(rank);
    }

    public void fileSelect(){
        dataDao.fileSelect();
    }

    public void fileAction(int score){
        //若文件不存在则创建文件
        this.fileCreat();
        //选定是哪种模式的文件
        this.fileSelect();
        //若文件存在则读取文件
        this.fileReader();
        //获取游戏结束时的分数
        this.setScore(score);
        //获取当前月份
        this.setMonth();
        //获取当前日
        this.setDay();
        //获取当前小时
        this.setHour();
        //获取当前分钟
        this.setMinute();
        //添加一条数据
        this.addData();
        //对得分进行排名
        this.sort();
        //将数据写入文件
        this.fileWriter();
    }

}
