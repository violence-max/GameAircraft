package edu.hitsz.ScorceData;

public interface DataDao {
    void sortData();
    void doAdd(int score, int month, int day, int hour, int minute);
    void creatFile();
    void fileWriter();
    void fileReader();
    void fileCopy();
    int getMonth();
    int getDay();
    int getHour();
    int getMinute();
}