package edu.hitsz;

import edu.hitsz.ScorceData.Data;
import edu.hitsz.application.Game;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class ScoreTable {
    private JPanel scoreTablePanel;
    private JPanel headerPanel;
    private JTable conctrectScoreTable;
    private JScrollPane socreTable;
    private JPanel bottomPanel;
    private JButton deletButoom;
    private JLabel concrectHeaderPanel;

    public ScoreTable(){
        //获取用户名字
        String userName = JOptionPane.showInputDialog("你当前的得分为:"+Game.dataPatternDemo.getScore()+"请输入你的名字:");
        //设置当前进行游戏的用户的名称
        Game.dataPatternDemo.setUserName(userName);
        LinkedList<Data> dataTable = Game.dataPatternDemo.getDataTable();
        String[] columnName = {"排名","玩家名称","得分","时间"};
        String[][] tableData = new String[dataTable.size()][4];

        for(int i=0; i<dataTable.size(); i++){
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
        conctrectScoreTable.setModel(model);
        socreTable.setViewportView(conctrectScoreTable);
        deletButoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = conctrectScoreTable.getSelectedRow();
                if(row != -1 && JOptionPane.showConfirmDialog(scoreTablePanel,"是否要删除当前选中的玩家的得分记录？","删除记录",0) == 0){
                    //删除一行数据
                    model.removeRow(row);
                    Game.dataPatternDemo.removeByRank(row+1);
                }
            }
        });
    }

    public JPanel getScoreTablePanel(){
        return scoreTablePanel;
    }
}