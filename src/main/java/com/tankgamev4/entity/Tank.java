package com.tankgamev4.entity;/*
 * Class Tank
 * @author Yunqi Cui
 * 27/02/2018
 */


public class Tank{

    //Tank Position
    public int x;
    public int y;
    public int type;
    public int direct;
    public int speed;
    public boolean isLive;


    public Tank(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getSpeed(){
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

}
