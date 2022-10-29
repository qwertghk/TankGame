package com.jin.TankGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener, Runnable {

    MyTank            myTank;
    Vector<EnemyTank> enemyTanks    = new Vector<>();
    int               enemyTankSize = 3;
    Vector<Bomb>      bombs         = new Vector<>();
    Image             image;

    public MyPanel() {
        myTank = new MyTank(400, 0);
        myTank.setSpeed(4);
        for (int i = 1; i <= enemyTankSize; i++) {
            EnemyTank enemyTank = new EnemyTank(100 * i, 0);
            enemyTank.setDirection(2);
            new Thread(enemyTank).start();
            Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirection());
            enemyTank.shots.add(shot);
            new Thread(shot).start();
            enemyTanks.add(enemyTank);
        }
        image = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/boom.gif"));

    }

    public void hitTank(Shot s, EnemyTank tank) {
        switch (tank.getDirection()) {
            case 0:
            case 2:
                if (s.x > tank.getX() && s.x < tank.getX() + 40
                        && s.y > tank.getY() && s.y < tank.getY() + 60) {
                    s.live = false;
                    tank.setLife(false);
                    enemyTanks.remove(tank);
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);
                }
            case 1:
            case 3:
                if (s.x > tank.getX() && s.x < tank.getX() + 60
                        && s.y > tank.getY() && s.y < tank.getY() + 40) {
                    s.live = false;
                    tank.setLife(false);
                    enemyTanks.remove(tank);

                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);
                }

        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);
        if (myTank.getLife()) {
            drawTank(myTank.getX(), myTank.getY(), g, myTank.getDirection(), 0);
        }

        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.getLife()) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirection(), 1);
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    Shot shot = enemyTank.shots.get(j);
                    if (shot.live) {
                        g.drawOval(shot.x, shot.y, 2, 2);

                    } else {
                        enemyTank.shots.remove(shot);
                    }
                }
            }
        }
        if (myTank.shot != null && myTank.shot.live && myTank.getLife()) {
            g.drawOval(myTank.shot.x, myTank.shot.y, 2, 2);
        }
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (bomb.life > 0) {
                g.drawImage(image, bomb.x, bomb.y, 260, 135, this);
                bomb.lifeDown();
            } else bombs.remove(bomb);


        }

    }

    public void drawTank(int x, int y, Graphics g, int direction, int type) {
        switch (type) {
            case 0://MyTank
                g.setColor(Color.cyan);
                break;
            case 1://other
                g.setColor(Color.yellow);
                break;
        }

        //0:↑ 1: →2: ↓ 3:←
        switch (direction) {
            case 0://up
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fill3DRect(x + 19, y, 2, 30, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                break;
            case 1://右
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fill3DRect(x + 30, y + 19, 30, 2, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                break;
            case 2://下
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fill3DRect(x + 19, y + 30, 2, 30, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                break;

            case 3://左
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fill3DRect(x, y + 19, 30, 2, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                break;

            default:
                System.out.println("未处理");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //0:↑ 1: →2: ↓ 3:←
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP && myTank.getLife()) {
            myTank.setDirection(0);
            myTank.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT && myTank.getLife()) {
            myTank.setDirection(1);
            myTank.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN && myTank.getLife()) {
            myTank.setDirection(2);
            myTank.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT && myTank.getLife()) {
            myTank.setDirection(3);
            myTank.moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_J && myTank.getLife()) {
            System.out.println("1");
            myTank.shotEnemyTank();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    @Override
    public void run() {
        while (myTank.getLife()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (myTank.shot != null && myTank.shot.live) {
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(myTank.shot, enemyTank);
                }
            }
//            for (EnemyTank enemyTank : enemyTanks) {
//                for (Shot shot : enemyTank.shots) {
//                    if (shot != null && shot.live)
//                        hitTank(shot, myTank);
//                }
//            }
            this.repaint();
        }
    }
}

