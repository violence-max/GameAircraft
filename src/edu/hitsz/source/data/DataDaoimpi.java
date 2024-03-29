package edu.hitsz.source.data;

import edu.hitsz.StartMenu;

import java.io.*;
import java.util.Calendar;
import java.util.LinkedList;


/**
 * @author 谢岸峰
 */
public class DataDaoimpi implements DataDao{

    private final LinkedList<Data> dataTable;
    /**
     * 存储得分榜中的数据
     */
    private final File easyModeFile = new File("src/easyModeData.txt");
    private final File commonModeFile = new File("src/commonModeData.txt");
    private final File hardModeFile = new File("src/hardModeData.txt");

    File file = null;


    private Integer rank;

    public DataDaoimpi(){
        dataTable = new LinkedList<>();
    }

    @Override
    public void sortData() {
        //如果数据链表里只有一个元素，则将该元素的ID设置成1
        if (dataTable.size() == 1){
            dataTable.getFirst().setDataId(1);
            rank = 1;
        }
        //插入的数据的分数为数据表中最大的
        if(dataTable.getLast().getScore() > dataTable.get(0).getScore()){
            rank = 1;
            dataTable.getLast().setDataId(1);
            for(int i=0; i<dataTable.size()-1; i++){
                //将未插入前数据表总的所有数据的排名减一
                dataTable.get(i).setDataId(i+2);
            }
        }
        else{
            /*
              对数据链表中除末尾以外的元素进行循环，将末尾元素插入合适的位置
             */
            for(int i=0; i<dataTable.size()-1; i++){
                //第i+1个数据不是未插入前的最后一个数据
                if(i+1 != dataTable.size()-1){
                    //插入的数据的分数在两者之间
                    if((dataTable.get(i).getScore() > dataTable.getLast().getScore()) && (dataTable.get(i+1).getScore() < dataTable.getLast().getScore())){
                        rank = i+2;
                        dataTable.getLast().setDataId(i+2);
                        for(int k=i+1; k<dataTable.size()-1; k++){
                            dataTable.get(k).setDataId(k+2);
                        }
                        break;
                    }
                    //插入的数据和某一数据的分数相等
                    else if(dataTable.get(i).getScore().equals(dataTable.getLast().getScore())) {
                        int j;
                        for (j = i + 1; j < dataTable.size() - 1; j++) {
                            //向下找到第一个不和第i+1个数据的分数相等的数据，记录j值
                            if (!dataTable.get(j).getScore().equals(dataTable.get(i).getScore())) {
                                break;
                            }
                        }
                        //对第j+1个数据的排名进行讨论
                        if (j == dataTable.size() - 2) {
                            //第i+1个数据的分数至第dataTable.size()-1个数据的分数均相等
                            if (dataTable.get(j).getScore().equals(dataTable.get(i).getScore())) {
                                //插入的数据排至最后
                                rank = j+2;
                                dataTable.getLast().setDataId(j + 2);
                            }
                            //第i+1个数据之后的数据至少有一个数据的分数比第i+1个数据的分数小
                            else {
                                //交换排名
                                rank = j+1;
                                dataTable.getLast().setDataId(j+1);
                                dataTable.get(j).setDataId(j+2);
                            }
                        }
                        else{
                            rank = j+1;
                            dataTable.getLast().setDataId(j+1);
                            for (int l = j; l < dataTable.size() - 1; l++) {
                                dataTable.get(l).setDataId(l + 2);
                            }
                        }
                        break;
                    }
                }
                else{
                    if(dataTable.get(i).getScore() < dataTable.getLast().getScore()){
                        //交换排名
                        rank = i+1;
                        dataTable.getLast().setDataId(i+1);
                        dataTable.get(i).setDataId(i+2);
                    }
                    else if(dataTable.get(i).getScore() > dataTable.getLast().getScore()){
                        rank = i+2;
                        dataTable.getLast().setDataId(i+2);
                    }
                    else{
                        rank = i+2;
                        dataTable.getLast().setDataId(i+2);
                    }
                }
            }
        }


    }

    @Override
    public void doAdd(int score, int month, int day, int hour, int minute) {
        Data data = new Data(score,month,day,hour,minute);
        dataTable.addLast(data);
        //在链表末尾添加一个元素
    }

    @Override
    public void creatFile() {
        if(StartMenu.difficultyMode == 0){
            //简单模式创建对应存数据的文件
            if(!easyModeFile.exists()) {
                try {
                    easyModeFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if(StartMenu.difficultyMode == 1){
            //普通模式创建对应的文件
            if(!commonModeFile.exists()) {
                try {
                    commonModeFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
            //困难模式创建对应的文件夹
            if(!hardModeFile.exists()) {
                try {
                    hardModeFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 向file中写入每一局的数据
     */
    @Override
    public void fileWriter() {
        try {
            if (file.exists()){
                //若文件存在则先删除再重建
                file.delete();
                this.creatFile();
                this.fileSelect();
            }
            //输出流和写入流
            FileOutputStream fop = new FileOutputStream(file);
            ObjectOutputStream writer = new ObjectOutputStream(fop);
            for (int i = 0; i < dataTable.size(); i++) {
                for (Data data : dataTable) {
                    if (i + 1 == data.getDataId()) {
                        writer.writeObject(data);
                    }
                }
            }
            writer.close();
            fop.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    @Override
    public void fileReader() {
        try{
            FileInputStream fip = new FileInputStream(file);
            ObjectInputStream reader  = new ObjectInputStream(fip);
            Data data = (Data) reader.readObject();
            while (data != null){
                dataTable.addLast(data);
                data = (Data) reader.readObject();
            }
            } catch (IOException | ClassNotFoundException e){

        }
    }

    @Override
    public int getMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH)+1;
    }

    @Override
    public int getDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DATE);
    }

    @Override
    public int getHour() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    @Override
    public int getMinute() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MINUTE);
    }

    @Override
    public LinkedList<Data> getDataTable() {
        return dataTable;
    }

    @Override
    public Integer getRank(){
        return rank;
    }

    @Override
    public void setUserName(String userName) {
        for (Data data : dataTable) {
            if (data.getDataId().equals(rank)) {
                data.setUserName(userName);
                this.fileWriter();
            }
        }
    }

    @Override
    public void removeByRank(int rank) {
        //记录原来数据集的数据个数
        int n = dataTable.size();
        //删除排名为rank的数据
        dataTable.removeIf(data -> data.getDataId().equals(rank));
        //对排名为rank+1以上的数据实行排名减一的操作
        for (int i=rank+1; i<=n; i++){
            for (Data data : dataTable){
                if (data.getDataId().equals(i)){
                    data.setDataId(i-1);
                }
            }
        }
        this.fileWriter();
    }

    @Override
    public void fileSelect() {
        if (StartMenu.difficultyMode == 0){
            file = easyModeFile;
        }else if (StartMenu.difficultyMode == 1){
            file = commonModeFile;
        }else{
            file = hardModeFile;
        }
    }
}
