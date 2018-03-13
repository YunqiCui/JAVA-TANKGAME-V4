package com.tankgamev4;/*
 * Class MyGraph to draw Tank
 * @author Yunqi Cui
 * 27/02/2018
 */


import com.tankgamev4.entity.Bullet;
import com.tankgamev4.entity.Bomb;
import com.tankgamev4.entity.Tank;
import com.tankgamev4.model.EnemyTank;
import com.tankgamev4.model.PlayerTank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyTank extends JPanel implements KeyListener, Runnable {

    private PlayerTank pt;
    private Vector<EnemyTank> etv = new Vector<EnemyTank>();
    private Vector<Bomb> bbv = new Vector<Bomb>();
    int enemySize = 3;
    public int x = 0;
    public int y = 0;
    Image im1,im2,im3;
    Thread t,t2;


    public MyTank() {
        pt = new PlayerTank(100, 250);
        pt.isLive = true;

        for (int i = 0; i < enemySize; i++) {
            EnemyTank et = new EnemyTank((i + 1) * 50, 0);
            et.isLive = true;
            et.setType(0);
            etv.add(et);
            Bullet eb = new Bullet(et.x,et.y,et.direct);
            et.ebv.add(eb);
            t = new Thread(et);
            t.start();
            t2 = new Thread(eb);
            t2.start();

        }

        x = pt.getX();
        y = pt.getY();

        //Define 3 images, combine them to make a bomb.
        im1 = Toolkit.getDefaultToolkit().getImage("src/main/resources/bomb_1.gif");
        im2 = Toolkit.getDefaultToolkit().getImage("src/main/resources/bomb_2.gif");
        im3 = Toolkit.getDefaultToolkit().getImage("src/main/resources/bomb_3.gif");
    }

    public void paint(Graphics g) {

        super.paint(g);
        this.drawGameField(g);
        //draw Player Tank
        if(pt.isLive){
            this.drawTank(pt.getX(), pt.getY(), g, pt.getDirect(), pt.getType());
        }


        //traverse every bullet from the vector
        for (int i = 0; i < this.pt.pbv.size(); i++) {
            Bullet mb = this.pt.pbv.get(i);

            //Draw Player Bullet
            if (mb != null && mb.isAlive) {
                g.draw3DRect(mb.x, mb.y, 1, 1, false);
            } else if (!mb.isAlive) {
                pt.pbv.remove(mb);
            }

        }

        //draw Enemy Tank
        for (int i = 0; i < enemySize; i++) {
            EnemyTank et = etv.get(i);
            if(et.isLive){
                this.drawTank(et.getX(), et.getY(), g, et.getDirect(), et.getType());
                for (int j = 0; j < et.ebv.size(); j++) {
                    Bullet eb = et.ebv.get(j);

                    //Draw Enemy Bullet
                    if (eb != null && eb.isAlive) {
                        g.draw3DRect(eb.x, eb.y, 1, 1, false);
                    } else if (!eb.isAlive) {
                        et.ebv.remove(eb);
                    }else{
                        break;
                    }
                }

            }
        }

        //draw Bomb

        for (int i = 0; i <bbv.size() ; i++) {
            Bomb bomb = bbv.get(i);

            //draw three different gif
            if(bomb.life > 6){
                g.drawImage(im3,bomb.x, bomb.y,30,30,this);
            }else if (bomb.life > 3){
                g.drawImage(im2,bomb.x, bomb.y,30,30,this);
            }else{
                g.drawImage(im1,bomb.x, bomb.y,30,30,this);
            }
            bomb.lifeDecrease();

            if(bomb.life <=0){
                bbv.remove(bomb);
            }
        }
    }

    public void drawGameField(Graphics g) {
        g.fillRect(0, 0, 400, 300);
    }

    //Draw Tank Function
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        //Tpye of Tank
        switch (type) {
            case 0:
                g.setColor(Color.cyan);
                break;

            case 1:
                g.setColor(Color.yellow);
                break;
        }
        switch (direct) {
            case 0:
                //Left Rectangular
                g.fill3DRect(x, y, 5, 30, false);
                //Middle Rectangular
                g.fill3DRect(x + 5, y + 5, 10, 20, false);
                //Right Rectangular
                g.fill3DRect(x + 15, y, 5, 30, false);
                //Circle
                g.fillOval(x + 7, y + 10, 6, 10);
                //UpLine
                g.drawLine(x + 10, y + 15, x + 10, y);
                break;

            case 1:
                //Up Rectangular
                g.fill3DRect(x, y, 30, 5, false);
                //Middle Rectangular
                g.fill3DRect(x + 5, y + 5, 20, 10, false);
                //Down Rectangular
                g.fill3DRect(x, y + 15, 30, 5, false);
                //Circle
                g.fillOval(x + 10, y + 7, 10, 6);
                //RightLine
                g.drawLine(x + 15, y + 10, x + 30, y + 10);
                break;

            case 2:
                //Left Rectangular
                g.fill3DRect(x, y, 5, 30, false);
                //Middle Rectangular
                g.fill3DRect(x + 5, y + 5, 10, 20, false);
                //Right Rectangular
                g.fill3DRect(x + 15, y, 5, 30, false);
                //Circle
                g.fillOval(x + 7, y + 10, 6, 10);
                //DownLine
                g.drawLine(x + 10, y + 15, x + 10, y + 30);
                break;

            case 3:
                //Up Rectangular
                g.fill3DRect(x, y, 30, 5, false);
                //Middle Rectangular
                g.fill3DRect(x + 5, y + 5, 20, 10, false);
                //Down Rectangular
                g.fill3DRect(x, y + 15, 30, 5, false);
                //Circle
                g.fillOval(x + 10, y + 7, 10, 6);
                //LeftLine
                g.drawLine(x + 15, y + 10, x, y + 10);
                break;
        }
    }


    public void keyTyped(KeyEvent e) {

    }

    //Move Tank by Keyboard
    public void keyPressed(KeyEvent e) {
        if(pt.isLive){

        if (e.getKeyCode() == KeyEvent.VK_UP
                ) {

            pt.setDirect(0);
            pt.moveUp();

        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

            pt.setDirect(1);
            pt.moveRight();

        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

            pt.setDirect(2);
            pt.moveDown();

        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {

            pt.setDirect(3);
            pt.moveLeft();

        } else {
            System.out.println("Not a direction key");
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {

            if (pt.pbv.size() <= 4) {
                pt.shotEnemy();
            }

        }
        this.repaint();
        }
    }

    public void keyReleased(KeyEvent e) {

    }
    //destroy player tank when hit by enemy bullet
    public void destroyPlayer(){

        for (int i = 0; i < etv.size(); i++) {
            EnemyTank et = etv.get(i);
            if(et.isLive){
                for (int j = 0; j < et.ebv.size(); j++) {
                    Bullet b = et.ebv.get(j);
                    if(b.isAlive){
                        hitTank(b,pt);
                    }
                }
            }
        }


    }
    //Destroy enemy tank when hit by player bullet
    public void destroyEnemy(){
        //判断是否击中坦克，判断那一个坦克，击中哪一个坦克
        for (int i = 0; i < this.pt.pbv.size(); i++) {

            Bullet b = pt.pbv.get(i);
            //
            if (b.isAlive) {
                for (int j = 0; j < etv.size(); j++) {
                    EnemyTank et = etv.get(j);
                    if (et.isLive) {
                        this.hitTank(b, et);
                    }
                }
            }

        }

    }
    //Function about when any bullet hit any of the tank.
    public void hitTank(Bullet b, Tank tk) {

        switch (tk.direct) {

            //Eenemy Tank is facing up or down
            case 0:
            case 2:
                if (b.x > tk.x && b.x < tk.x + 20 && b.y > tk.y && b.y < tk.y + 30) {
                    //hit
                    b.isAlive = false;
                    tk.isLive = false;
                    Bomb bomb = new Bomb(tk.x,tk.y);
                    bbv.add(bomb);
                }

            case 1:
            case 3:
                if (b.x > tk.x && b.x < tk.x + 30 && b.y > tk.y && b.y < tk.y + 20) {
                    //hit
                    b.isAlive = false;
                    tk.isLive = false;
                    Bomb bomb = new Bomb(tk.x,tk.y);
                    bbv.add(bomb);
                }
        }

    }
    //Function to be run when Player wins
    public void playerWin(){

            int etn = etv.size();
            for (int i = 0; i < etv.size() ; i++) {
            EnemyTank et = etv.get(i);
            if(!et.isLive){
                etn--;
                if(etn==0){
                    t.interrupt();
                    t2.interrupt();
                    JOptionPane.showMessageDialog(this, "Congratulations, You Win!");
                    System.exit(0);
                }
            }else{
                break;
            }
            }


    }

    public void playerLose(){
        t.interrupt();
        t2.interrupt();
        JOptionPane.showMessageDialog(this, "Game Over!");
        System.exit(0);
    }

    public void run() {

        //Every 100ms repaint the Bullet
        while (true) {
            try {
                Thread.sleep(30);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.destroyEnemy();
            this.destroyPlayer();
            this.repaint();
            if(!pt.isLive){
                this.playerLose();
                break;
            }else {
                playerWin();
            }
        }

    }
}
