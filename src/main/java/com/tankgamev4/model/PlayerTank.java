package com.tankgamev4.model;/*
 * Class PlayerTank
 * @author Yunqi Cui
 * 27/02/2018
 */


import com.tankgamev4.entity.Bullet;
import com.tankgamev4.entity.Tank;

import java.util.Vector;

public class PlayerTank extends Tank {


    public Vector<Bullet> pbv = new Vector<Bullet>();
    public Bullet b=null;
    //up:0 right:1 down:2 left:3

    public PlayerTank(int x, int y) {
        super(x, y);
        this.setDirect(0);
        this.setSpeed(5);
        this.setType(1);
    }


    public void moveUp(){
        if(this.y>0){
        this.y-=this.getSpeed();
        }
    }

    public void moveRight(){
        if(x<370){
        this.x+=this.getSpeed();
        }
    }

    public void moveDown(){
        if(y<270){
        this.y+=this.getSpeed();
        }
    }

    public void moveLeft(){
        if(x>0){
        this.x-=this.getSpeed();
        }
    }
    public void shotEnemy(){
        //Player Tank shot
        switch (this.direct){
            case 0:
                b = new Bullet(x+10,y,0);
                pbv.add(b);
                break;

            case 1:
                b = new Bullet(x+30,y+10,1);
                pbv.add(b);
                break;

            case 2:
                b = new Bullet(x+10,y+30,2);
                pbv.add(b);
                break;

            case 3:
                b = new Bullet(x,y+10,3);
                pbv.add(b);
                break;
        }
        Thread t = new Thread(b);
        t.start();
    }



}
