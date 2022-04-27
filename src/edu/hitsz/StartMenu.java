package edu.hitsz;

import edu.hitsz.application.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu {
    private JPanel TopPanel;
    private JPanel BottomPanel;
    private JButton EasyGame;
    private JButton NormalGame;
    private JButton HardGame;
    private JLabel Music;
    private JComboBox MusicSelet;
    private JPanel StartMenu;

    public StartMenu() {
        EasyGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartMenu.setVisible(false);
                Main.IS_EASY_GAME = true;
                synchronized (Main.class){
                    //选定难度，通知主线程等待结束
                    Main.class.notify();
                }
            }
        });
        NormalGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartMenu.setVisible(false);
                Main.IS_NORMAL_GAME = true;
                synchronized (Main.class){
                    //选定难度，通知主线程等待结束
                    Main.class.notify();
                }
            }
        });
        HardGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartMenu.setVisible(false);
                Main.IS_HARD_GAME = true;
                synchronized (Main.class){
                    //选定难度，通知主线程等待结束
                    Main.class.notify();
                }
            }
        });
    }

    public JComboBox getMusicSelet(){
        return MusicSelet;
    }

    public JPanel getStartMenu(){
        return StartMenu;
    }
}
