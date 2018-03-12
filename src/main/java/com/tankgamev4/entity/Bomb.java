package com.tankgamev4.entity;

public class Bomb {

    public int x,y;

    //
    public int life = 9;
    public boolean isAlive = true;

    public Bomb(int x, int y){
        this.x = x;
        this.y = y;
    }
    //Life of Bomb 
    public void lifeDecrease(){
        if(life>0){
            life--;
        }else{
            this.isAlive = false;
        }
    }


}
