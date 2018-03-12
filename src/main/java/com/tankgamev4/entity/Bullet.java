package com.tankgamev4.entity;

public class Bullet implements Runnable{

    public int x;
    public int y;
    public int direct;
    public int speed =3;
    public boolean isAlive = true;

    public Bullet(int x, int y,int direct){
        this.x= x;
        this.y= y;
        this.direct = direct;
    }
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public void run() {

        while(true){
        try{
            Thread.sleep(20);
        }catch (Exception e){
            e.printStackTrace();
        }
            //Bullet Shot
            switch(direct){
                //Bullet ShotUp
                case 0:
                    y-=speed;
                    break;
                //Bullet ShotRight
                case 1:
                    x+=speed;
                    break;
                //Bullet ShotDown
                case 2:
                     y+=speed;
                     break;
                //Bullet ShotLeft
                case 3:
                    x-=speed;
                    break;
            }
//            System.out.println("Bullet X = " +x + " Y = " +y);
            //when THE Bullet need to be killed
            if(x<0 || x > 400 || y < 0 || y> 300){
                isAlive = false;
                break;
            }

    }
    }
}
