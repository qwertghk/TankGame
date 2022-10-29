package com.jin.TankGame;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {

    Vector<Shot> shots = new Vector<>();

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    private Boolean loop=true;

    public Boolean getLoop() {
        return loop;
    }

    public void setLoop(Boolean loop) {
        this.loop = loop;
    }

    @Override
    public void run() {
        setSpeed(1);
        while (loop) {
            switch (getDirection()) {
                case 0:
                    for (int i = 0; i < 30 && getY() > 0; i++) {
                        moveUp();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    break;
                case 1:
                    for (int i = 0; i < 30 && getX() + 60 < 1000; i++) {
                        moveRight();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    break;
                case 2:
                    for (int i = 0; i < 30 && getY() + 60 < 750; i++) {
                        moveDown();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    break;
                case 3:
                    for (int i = 0; i < 30 && getX() > 0; i++) {
                        moveLeft();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    break;
            }

            this.setDirection((int) (Math.random() * 4));
            if (!getLife()) {
                break;
            }
        }


    }
}

