package edu.hitsz.ScorceData;

import java.util.LinkedList;

public interface DataDao {
    void sortData();
    void doAdd(int score, int month, int day, int hour, int minute);
    void creatFile();
    void fileWriter();
    void fileReader();
    int getMonth();
    int getDay();
    int getHour();
    int getMinute();
    LinkedList<Data> getDataTable();
    Integer getRank();
    void setUserName(String userName);
    void removeByRank(int rank);
    void fileSelect();
}
