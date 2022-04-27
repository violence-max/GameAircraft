package edu.hitsz.application;

import javax.swing.*;
import java.awt.*;

import edu.hitsz.*;

/**
 * 程序入口
 * @author hitsz
 */
public class Main {

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;
    public static boolean IS_MUSIC = false;
    public static boolean IS_EASY_GAME = false;
    public static boolean IS_NORMAL_GAME = false;
    public static boolean IS_HARD_GAME = false;


    public static void main(String[] args) {

        System.out.println("Hello Aircraft War");

        // 获得屏幕的分辨率，初始化 Frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("Aircraft War");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        //设置窗口的大小和位置,居中放置
        frame.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //第一个界面，菜单选择界面
        StartMenu startMenu = new StartMenu();
        JPanel startMenuPanel = startMenu.getStartMenu();
        frame.setContentPane(startMenuPanel);
        frame.setVisible(true);

        synchronized (Main.class){
            try {
                Main.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //获取音效选择
        if (startMenu.getMusicSelet().getSelectedItem() == ("open")){
            IS_MUSIC = true;
        }else{
            IS_MUSIC = false;
        }

        //移除第一个页面
        frame.remove(startMenuPanel);

        //第二个界面，游戏界面
        Game game = new Game();
        frame.setContentPane(game);
        frame.setVisible(true);
        game.action();

        synchronized (Main.class){
            try {
                Main.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //移除游戏界面
        frame.remove(game);

        //第三个界面，得分排行榜界面
        ScoreTable scoreTable = new ScoreTable();
        JPanel panel = scoreTable.getScoreTablePanel();
        panel.setVisible(true);
        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}
