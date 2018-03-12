package com.tankgamev4.model;

import com.tankgamev4.entity.Bullet;
import com.tankgamev4.entity.Tank;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {

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
                        if (this.y > 0) {
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
                        if (this.x < 370) {
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
                        if (this.y < 270) {
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
                        if (this.x > 0) {
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
            //Ememy Tank Generate Bullet
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
}
