package edu.hitsz.ScorceData;

import java.io.*;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Scanner;

import static java.util.Collections.reverseOrder;
import static java.util.Collections.sort;

public class DataDaoimpi implements DataDao{

    private LinkedList<Data> dataTable;
    /**
     * 存储得分榜中的数据
     */
    private File file = new File("./Datas.txt");
    /**
     * 只存储数据表中的名次、分数和日期与时间的文件
     */
    private File readOnlyFile = new File("./ReadOnly.txt");

    public DataDaoimpi(){
        dataTable = new LinkedList<>();
    }

    @Override
    public void sortData() {
        if (dataTable.size() == 1){
            dataTable.getFirst().setDataId(1);
            //如果数据链表里只有一个元素，则将该元素的ID设置成1
        }
        //插入的数据的分数为数据表中最大的
        if(dataTable.getLast().getScore() > dataTable.get(0).getScore()){
            dataTable.getLast().setDataId(1);
            for(int i=0; i<dataTable.size()-1; i++){
                //将未插入前数据表总的所有数据的排名减一
                dataTable.get(i).setDataId(i+2);
            }
        }
        else{
            /**
             * 对数据链表中除末尾以外的元素进行循环，将末尾元素插入合适的位置
             */
            for(int i=0; i<dataTable.size()-1; i++){
                //第i+1个数据不是未插入前的最后一个数据
                if(i+1 != dataTable.size()-1){
                    //插入的数据的分数在两者之间
                    if((dataTable.get(i).getScore() > dataTable.getLast().getScore()) && (dataTable.get(i+1).getScore() < dataTable.getLast().getScore())){
                        dataTable.getLast().setDataId(i+2);
                        for(int k=i+1; k<dataTable.size()-1; k++){
                            dataTable.get(k).setDataId(k+2);
                        }
                        break;
                    }
                    //插入的数据和某一数据的分数相等
                    else if(dataTable.get(i).getScore() == dataTable.getLast().getScore()) {
                        int j;
                        for (j = i + 1; j < dataTable.size() - 1; j++) {
                            //向下找到第一个不和第i+1个数据的分数相等的数据，记录j值
                            if (dataTable.get(j).getScore() != dataTable.get(i).getScore()) {
                                break;
                            }
                        }
                        //对第j+1个数据的排名进行讨论
                        if (j == dataTable.size() - 2) {
                            //第i+1个数据的分数至第dataTable.size()-1个数据的分数均相等
                            if (dataTable.get(j).getScore() == dataTable.get(i).getScore()) {
                                //插入的数据排至最后
                                dataTable.getLast().setDataId(j + 2);
                                break;
                            }
                            //第i+1个数据之后的数据至少有一个数据的分数比第i+1个数据的分数小
                            else {
                                //交换排名
                                dataTable.getLast().setDataId(j+1);
                                dataTable.get(j).setDataId(j+2);
                                break;
                            }
                        }
                        else{
                            dataTable.getLast().setDataId(j+1);
                            for (int l = j; l < dataTable.size() - 1; l++) {
                                dataTable.get(l).setDataId(l + 2);
                            }
                            break;
                        }
                    }
                }
                else{
                    if(dataTable.get(i).getScore() < dataTable.getLast().getScore()){
                        //交换排名
                        dataTable.getLast().setDataId(i+1);
                        dataTable.get(i).setDataId(i+2);
                    }
                    else if(dataTable.get(i).getScore() > dataTable.getLast().getScore()){
                        dataTable.getLast().setDataId(i+2);
                    }
                    else{
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
        if(!file.exists()) {
            //在AircraftWar-base目录下创建文件
            file.getParentFile().mkdir();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(!readOnlyFile.exists()){
            readOnlyFile.getParentFile().mkdir();
            try {
                readOnlyFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 向file中写入每一局的数据
     */
    @Override
    public void fileWriter() {
        try {
            if(file.exists()) {
                //先删除，后重写文件
                file.delete();
                readOnlyFile.delete();
                this.creatFile();

                //输出流和写入流
                FileOutputStream fop = new FileOutputStream(file);
                OutputStreamWriter writer = new OutputStreamWriter(fop);
                writer.append("******************************************" + "\n");
                writer.append("                  得分排行榜                " + "\n");
                writer.append("******************************************" + "\n");
                for (int i = 0; i < dataTable.size(); i++) {
                    for (int j = 0; j < dataTable.size(); j++) {
                        if (i + 1 == dataTable.get(j).getDataId()) {
                            writer.append("第" + dataTable.get(j).getDataId() + "名" + ":" + dataTable.get(j).getUserName() + "," + dataTable.get(j).getScore() + "," + dataTable.get(j).getMonth() + "月" + dataTable.get(j).getDay() + "日" + "," + dataTable.get(j).getHour() + "点" + dataTable.get(j).getMinute() + "分" + "\n");
                        }
                    }
                }
                writer.close();
                fop.close();
            }
            if(readOnlyFile.exists()){
                FileOutputStream ffop =new FileOutputStream(readOnlyFile);
                OutputStreamWriter fwriter = new OutputStreamWriter(ffop);
                for(int k=0; k<dataTable.size(); k++){
                    for(int l=0; l<dataTable.size(); l++){
                        if(k+1 == dataTable.get(l).getDataId()){
                            //以"|"为分隔符
                            if(k < dataTable.size()-1){
                                fwriter.append(dataTable.get(l).getDataId()+"|"+dataTable.get(l).getScore()+"|"+dataTable.get(l).getMonth()+"|"+dataTable.get(l).getDay()+"|"+dataTable.get(l).getHour()+"|"+dataTable.get(l).getMinute()+"|");
                            }
                            else{
                                fwriter.append(dataTable.get(l).getDataId()+"|"+dataTable.get(l).getScore()+"|"+dataTable.get(l).getMonth()+"|"+dataTable.get(l).getDay()+"|"+dataTable.get(l).getHour()+"|"+dataTable.get(l).getMinute());
                            }
                            break;
                        }
                    }
                }
                //关闭写入流和输出流
                fwriter.close();
                ffop.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 读取文件
     */
    @Override
    public void fileReader() {
        if(file.exists()){
            try {
                //输入流和读取流
                FileInputStream fip = new FileInputStream(file);
                InputStreamReader reader = new InputStreamReader(fip);
                StringBuffer stringBuffer = new StringBuffer();
                while(reader.ready()){
                    stringBuffer.append((char)reader.read());
                }
                System.out.println(stringBuffer.toString());
                //关闭读取流和输入流
                reader.close();
                fip.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        else{
            System.out.println("文件不存在");
        }
    }

    @Override
    public void fileCopy() {
        try{
            Scanner scanner = new Scanner(new FileReader(readOnlyFile));
            scanner.useDelimiter("\\|");
            while(scanner.hasNextInt()){
                //循环读取文件中的数据，在dataTable中创建并添加对应的Data
                int rank = scanner.nextInt();
                int score = scanner.nextInt();
                int month = scanner.nextInt();
                int day = scanner.nextInt();
                int hour = scanner.nextInt();
                int minute = scanner.nextInt();
                this.doAdd(score,month,day,hour,minute);
                dataTable.getLast().setDataId(rank);
            }
        }catch (IOException e){
            e.printStackTrace();
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

}
