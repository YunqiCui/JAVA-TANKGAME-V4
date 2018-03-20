package com.tankgamev4.gui;

import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel implements Runnable{


    int times= 0;
    public void paint(Graphics g){
        super.paint(g);
        g.fillRect(0,0,400,300);

        if(times%2==0){
            g.setColor(Color.YELLOW);
            g.drawString("Stage: 1" ,180,150);
        }

    }

    public void run(){
        while(true){
            try{
                Thread.sleep(100);
            }catch(Exception e){
                e.printStackTrace();
            }
            times++;
            if(times<=50){
                this.repaint();
            }else{
                break;
            }
        }
    }

}
