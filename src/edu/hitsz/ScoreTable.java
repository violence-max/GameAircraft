package edu.hitsz;

import edu.hitsz.ScorceData.Data;
import edu.hitsz.application.Game;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.LinkedList;

public class ScoreTable {
    private JPanel ScoreTablePanel;
    private JPanel HeaderPanel;
    private JTable ConctrectScoreTable;
    private JScrollPane SocreTable;

    public ScoreTable(){
        //获取用户名字
        String userName = JOptionPane.showInputDialog("你当前的得分为:"+Game.dataPatternDemo.getScore()+"请输入你的名字:");
        LinkedList<Data> dataTable = Game.dataPatternDemo.getDataTable();
        String[] columnName = {"排名","玩家名称","得分","时间"};
        String[][] tableData = new String[dataTable.size()][4];

        for(int i=0; i<dataTable.size(); i++){
            if(dataTable.get(i).getDataId().equals(Game.dataPatternDemo.getRank())){
                //设置当前进行游戏的用户的名称
                dataTable.get(i).setUserName(userName);
            }
            for(int j=0; j<dataTable.size(); j++){
                if(i+1 == dataTable.get(j).getDataId()){
                    tableData[i][0] = dataTable.get(j).getDataId().toString();
                    tableData[i][1] = dataTable.get(j).getUserName();
                    tableData[i][2] = dataTable.get(j).getScore().toString();
                    tableData[i][3] = dataTable.get(j).getMonth().toString() + "月" + dataTable.get(j).getDay().toString() + "日" + dataTable.get(j).getHour() + "时" + dataTable.get(j).getMinute() + "分";
                }
            }
        }
        //表格模型
        DefaultTableModel model = new DefaultTableModel(tableData, columnName){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        //从表格模型那里获取数据
        ConctrectScoreTable.setModel(model);
        SocreTable.setViewportView(ConctrectScoreTable);
    }

    public JPanel getScoreTablePanel(){
        return ScoreTablePanel;
    }
}
