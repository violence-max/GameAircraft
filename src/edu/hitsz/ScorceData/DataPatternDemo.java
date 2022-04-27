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

    public void fileWrite(){dateDao.fileWriter();}

    public void fileReader(){
        dateDao.fileReader();
    }

    public void filePrint(){dateDao.filePrint();}

    public void fileAction(int score){
        //若文件不存在则创建文件
        this.fileCreat();
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
        this.fileWrite();
        //打印得分排行榜
        this.filePrint();
    }

}
