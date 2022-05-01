package edu.hitsz;

import edu.hitsz.source.data.Data;
import edu.hitsz.application.AbstractGame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * @author 谢岸峰
 */
public class ScoreTable {
    private JPanel scoreTablePanel;
    private JPanel headerPanel;
    private JTable contentScoreTable;
    private JScrollPane scoreTable;
    private JPanel bottomPanel;
    private JButton deleteButton;
    private JLabel concrectHeaderPanel;
    private JLabel modeLabel;

    public ScoreTable(){
        if(StartMenu.difficultyMode == 0){
            String easyLabelText = "难度：简单";
            this.setLabelText(easyLabelText);
        }else if (StartMenu.difficultyMode == 1){
            String commonLabelText = "难度：普通";
            this.setLabelText(commonLabelText);
        }else{
            String hardLabelText = "难度：困难";
            this.setLabelText(hardLabelText);
        }
        //获取用户名字
        String userName = JOptionPane.showInputDialog("你当前的得分为:"+ AbstractGame.dataPatternDemo.getScore()+"请输入你的名字:");
        //设置当前进行游戏的用户的名称
        AbstractGame.dataPatternDemo.setUserName(userName);
        LinkedList<Data> dataTable = AbstractGame.dataPatternDemo.getDataTable();
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
        contentScoreTable.setModel(model);
        scoreTable.setViewportView(contentScoreTable);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = contentScoreTable.getSelectedRow();
                String message = "是否要删除当前选中的玩家的得分记录？";
                String title = "删除记录";
                if(row != -1 && JOptionPane.showConfirmDialog(scoreTablePanel,message,title,0) == 0){
                    //删除一行数据
                    model.removeRow(row);
                    AbstractGame.dataPatternDemo.removeByRank(row+1);
                }
            }
        });
    }

    public JPanel getScoreTablePanel(){
        return scoreTablePanel;
    }

    public void setLabelText(String labelText){
        modeLabel.setText(labelText);
    }
}
