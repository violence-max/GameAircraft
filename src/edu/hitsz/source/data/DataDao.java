package edu.hitsz.source.data;

import java.util.LinkedList;

/**
 * @author 谢岸峰
 */
public interface DataDao {
    /**
     * 对数据排序
     */
    void sortData();

    /**
     * 添加一条数据
     * @param score
     * @param month
     * @param day
     * @param hour
     * @param minute
     */
    void doAdd(int score, int month, int day, int hour, int minute);

    /**
     * 创建文件夹
     */
    void creatFile();

    /**
     * 将数据写入文件
     */
    void fileWriter();

    /**
     * 读文件
     */
    void fileReader();

    /**
     * 获取当前月
     * @return
     */
    int getMonth();

    /**
     * 获取当前天
     * @return
     */
    int getDay();

    /**
     * 获取当前小时
     * @return
     */
    int getHour();

    /**
     * 获取当前分钟
     * @return
     */
    int getMinute();

    /**
     * 获取数据链表
     * @return
     */
    LinkedList<Data> getDataTable();

    /**
     * 获取当前局所插入的数据的排名
     * @return
     */
    Integer getRank();

    /**
     * 为当前局的玩家提供输入名称需求
     * @param userName
     */
    void setUserName(String userName);

    /**
     * 通过排名删除数据
     * @param rank
     */
    void removeByRank(int rank);

    /**
     * 选择不同模式下的数据文件夹
     */
    void fileSelect();
}
