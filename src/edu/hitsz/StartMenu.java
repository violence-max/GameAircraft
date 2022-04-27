package edu.hitsz;

import edu.hitsz.application.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu {
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JButton easyGame;
    private JButton normalGame;
    private JButton hardGame;
    private JLabel music;
    private JComboBox musicSelet;
    private JPanel startMenu;

    public StartMenu() {
        easyGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startMenu.setVisible(false);
                Main.IS_EASY_GAME = true;
                synchronized (Main.class){
                    //选定难度，通知主线程等待结束
                    Main.class.notify();
                }
            }
        });
        normalGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startMenu.setVisible(false);
                Main.IS_NORMAL_GAME = true;
                synchronized (Main.class){
                    //选定难度，通知主线程等待结束
                    Main.class.notify();
                }
            }
        });
        hardGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startMenu.setVisible(false);
                Main.IS_HARD_GAME = true;
                synchronized (Main.class){
                    //选定难度，通知主线程等待结束
                    Main.class.notify();
                }
            }
        });
    }

    public JComboBox getMusicSelet(){
        return musicSelet;
    }

    public JPanel getStartMenu(){
        return startMenu;
    }
}
