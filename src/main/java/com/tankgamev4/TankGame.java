package com.tankgamev4;
/*
 * TankGame Version1
 * @author Yunqi Cui
 * 27/02/2018
 * @version 4.0
 * Use Thread let the Tank can shot bullet.
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TankGame extends JFrame implements ActionListener {

    //    MyTank mtk = new MyTank();
    JMenuBar jmb;
    JMenu jm1;
    JMenuItem jmi1;
    StartPanel sp;

    public static void main(String[] args) {
        TankGame tg1 = new TankGame();
    }

    public TankGame() {

        jmb = new JMenuBar();
        jm1 = new JMenu("Game(G)");
        jm1.setMnemonic('G');
        jmi1 = new JMenuItem("Start Game(N)");
        jmi1.setMnemonic('N');
        jmi1.addActionListener(this);
        jmi1.setActionCommand("newGame");

        jm1.add(jmi1);
        jmb.add(jm1);

        sp = new StartPanel();
        Thread t = new Thread(sp);
        t.start();
        this.add(sp);
        this.setJMenuBar(jmb);
        this.setTitle("Tank Game");
        this.setSize(600, 500);
        this.setLocation(200, 200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
//        this.addKeyListener(mtk);
    }


    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("newGame")){
        MyTank mtk = new MyTank();
        this.remove(sp);
        this.add(mtk);
        this.addKeyListener(mtk);
        this.setVisible(true);
        //Start mtk Thread
        Thread t = new Thread(mtk);
        t.start();
        }
    }
}
