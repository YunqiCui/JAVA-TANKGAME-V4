package com.tankgamev4.gui;
/*
 * TankGame Version1
 * @author Yunqi Cui
 * 27/02/2018
 * @version 4.0
 * Use Thread let the Tank can shot bullet.
 */

import com.tankgamev4.model.Record;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TankGame extends JFrame implements ActionListener {

    MyTank mtk;
    JMenuBar jmb;
    JMenu jm1;
    JMenuItem jmi1,jmi2,jmi3,jmi4;
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


        jmi2 = new JMenuItem("Save and Exit(C)");
        jmi2.setMnemonic('C');
        jmi2.addActionListener(this);
        jmi2.setActionCommand("save and exit");

        jmi3 = new JMenuItem("Load Game(L)");
        jmi3.setMnemonic('L');
        jmi3.addActionListener(this);
        jmi3.setActionCommand("loadGame");

        jmi4 = new JMenuItem("Exit(E)");
        jmi4.setMnemonic('E');
        jmi4.addActionListener(this);
        jmi4.setActionCommand("exit");

        jm1.add(jmi1);
        jm1.add(jmi2);
        jm1.add(jmi3);
        jm1.add(jmi4);
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
    }


    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("newGame")){
        mtk = new MyTank("newGame");
        this.remove(sp);
        this.add(mtk);
        this.addKeyListener(mtk);
        this.setVisible(true);
        //Start mtk Thread
        Thread t = new Thread(mtk);
        t.start();

        }else if(e.getActionCommand().equals("save and exit")){

            Record rd = new Record();
            rd.setEtv(mtk.etv);
            rd.saveGames();
            System.exit(0);

        }else if(e.getActionCommand().equals("loadGame")){
//
//            Record rd = new Record();
//            rd.loadGames();
            mtk = new MyTank("load");
            this.remove(sp);
            this.add(mtk);
            this.addKeyListener(mtk);
            this.setVisible(true);
            //Start mtk Thread
            Thread t = new Thread(mtk);
            t.start();

        }else if(e.getActionCommand().equals("exit")){
            System.exit(0);
        }
    }
}
