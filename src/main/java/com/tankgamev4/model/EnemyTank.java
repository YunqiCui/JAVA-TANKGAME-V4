package com.tankgamev4.model;

import com.tankgamev4.entity.Bullet;
import com.tankgamev4.entity.Tank;

import java.util.Arrays;
import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {


	public Vector<EnemyTank> etv = new Vector<EnemyTank>();
    public Vector<Bullet> ebv = new Vector<Bullet>();
    public Bullet b = null;

    public EnemyTank(int x, int y) {
        super(x, y);
        this.setDirect((int) (Math.random() * 4));
        this.setType(0);
        this.setSpeed(2);
    }

    public void run() {

        while (true) {
            //Enemy Tank Run
            switch (this.direct) {

                case 0:

                    for (int i = 0; i < 30; i++) {
                        if (this.y > 0 && !isOverlap()) {
                            this.y -= speed;
                        } else {
                            break;
                        }
                        try {
                            Thread.sleep(50);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;

                case 1:

                    for (int i = 0; i < 30; i++) {
                        if (this.x < 370 && !isOverlap()) {
                            this.x += speed;
                        } else {
                            break;
                        }
                        try {
                            Thread.sleep(50);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;

                case 2:

                    for (int i = 0; i < 30; i++) {
                        if (this.y < 270 && !isOverlap()) {
                            this.y += speed;
                        } else {
                            break;
                        }
                        try {
                            Thread.sleep(50);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;

                case 3:

                    for (int i = 0; i < 30; i++) {
                        if (this.x > 0 && !isOverlap()) {
                            this.x -= speed;
                        } else {
                            break;
                        }
                        try {
                            Thread.sleep(50);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }

            this.direct = (int) (Math.random() * 4);
            //Enemy Tank Generate Bullet
            if (this.isLive) {
                if (ebv.size() < 5) {

                    switch (this.direct) {
                        case 0:
                            b = new Bullet(x + 10, y, 0);
                            ebv.add(b);
                            break;

                        case 1:
                            b = new Bullet(x + 30, y + 10, 1);
                            ebv.add(b);
                            break;

                        case 2:
                            b = new Bullet(x + 10, y + 30, 2);
                            ebv.add(b);
                            break;

                        case 3:
                            b = new Bullet(x, y + 10, 3);
                            ebv.add(b);
                            break;
                    }

                    Thread t = new Thread(b);
                    t.start();
                }
            }
            if (!this.isLive) {
                break;
            }
        }
    }

    public void setEtv(Vector<EnemyTank> vv){
        this.etv = vv;
    }

    public boolean isOverlap(){
    	
    	boolean b = false;

    	switch(this.direct){

            case 0:
                //my tank facing up
                for (int i = 0; i < etv.size(); i++) {

                    EnemyTank et = etv.get(i);
                    if (et != this) {
                        //Eneym Tank Up or Down
                        if(et.direct==0 || et.direct == 2){
                            //Left point
                            if(this.x>=et.x&&this.x<=et.x+20&&this.y>=et.y&&this.y<=et.y+30){
                                return true;
                            }
                            //right point
                            if(this.x+20>=et.x&&this.x+20<=et.x+20&&this.y>=et.y&&this.y<=et.y+30){
                                return true;
                            }
                        }
                        if(et.direct==1||et.direct==3){
                            //Left point
                            if(this.x>=et.x&&this.x<=et.x+30&&this.y>=et.y&&this.y<=et.y+20){
                                return true;
                            }
                            //right point
                            if(this.x+20>=et.x&&this.x+30<=et.x+20&&this.y>=et.y&&this.y<=et.y+20){
                                return true;
                            }
                        }
                    }
                }
                break;

            case 1:
                //Facing Right
                for (int i = 0; i < etv.size(); i++) {

                    EnemyTank et = etv.get(i);
                    if (et != this) {
                        //Eneym Tank Up or Down
                        if(et.direct==0 || et.direct == 2){
                            //Left point
                            if(this.x+30>=et.x&&this.x+30<=et.x+20&&this.y>=et.y&&this.y<=et.y+30){
                                return true;
                            }
                            //right point
                            if(this.x+30>=et.x &this.x+30<=et.x+20&&this.y+20>=et.y&&this.y+20<=et.y+30){
                                return true;
                            }
                        }
                        //Enemy Left Or Right
                        if(et.direct==1||et.direct==3){
                            //Left point
                            if(this.x+30>=et.x&&this.x+30<=et.x+30&&this.y>=et.y&&this.y<=et.y+20){
                                return true;
                            }
                            //right point
                            if(this.x+30>=et.x&&this.x+30<=et.x+30&&this.y+20>=et.y&&this.y+20<=et.y+20){
                                return true;
                            }
                        }
                    }
                }
                break;

            case 2:
                //Facing Down
                for (int i = 0; i < etv.size(); i++) {

                    EnemyTank et = etv.get(i);
                    if (et != this) {
                        //Enemy Tank Up or Down
                        if(et.direct==0 || et.direct == 2){
                            //Left point
                            if(this.x>=et.x&&this.x<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30){
                                return true;
                            }
                            //right point
                            if(this.x+20>=et.x&&this.x+20<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30){
                                return true;
                            }
                        }
                        if(et.direct==1||et.direct==3){
                            //Left point
                            if(this.x>=et.x&&this.x<=et.x+30&&this.y+30>=et.y&&this.y+30<=et.y+20){
                                return true;
                            }
                            //right point
                            if(this.x+20>=et.x&&this.x+30<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+20){
                                return true;
                            }
                        }
                    }
                }
                break;

            case 3:
                //Facing Left
                for (int i = 0; i < etv.size(); i++) {

                    EnemyTank et = etv.get(i);
                    if (et != this) {
                        //Eneym Tank Up or Down
                        if(et.direct==0 || et.direct == 2){
                            //Left point
                            if(this.x>=et.x&&this.x<=et.x+20&&this.y>=et.y&&this.y<=et.y+30){
                                return true;
                            }
                            //right point
                            if(this.x>=et.x &this.x<=et.x+20&&this.y+20>=et.y&&this.y+20<=et.y+30){
                                return true;
                            }
                        }
                        //Enemy Left Or Right
                        if(et.direct==1||et.direct==3){
                            //Left point
                            if(this.x>=et.x&&this.x<=et.x+30&&this.y>=et.y&&this.y<=et.y+20){
                                return true;
                            }
                            //right point
                            if(this.x>=et.x&&this.x<=et.x+30&&this.y+20>=et.y&&this.y+20<=et.y+20){
                                return true;
                            }
                        }
                    }
                }
                break;
        }
    	return b;
    }



}

