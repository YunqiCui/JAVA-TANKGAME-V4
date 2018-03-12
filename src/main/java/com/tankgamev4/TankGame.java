package com.tankgamev4;
/*
 * TankGame Version1
 * @author Yunqi Cui
 * 27/02/2018
 * @version 4.0
 * Use Thread let the Tank can shot bullet.
 */

import javax.swing.*;

public class TankGame extends JFrame {

    MyTank mtk = new MyTank();

    public static void main(String[] args) {

        TankGame tg1 = new TankGame();

    }

    public TankGame() {

        this.add(mtk);
        //Start mtk Thread
        Thread t = new Thread(mtk);
        t.start();

        this.setTitle("Tank Game");
        this.setSize(400, 300);
        this.setLocation(200, 200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addKeyListener(mtk);
    }

}
