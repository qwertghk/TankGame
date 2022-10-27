package com.jin.TankGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener ,Runnable{

    MyTank            myTank;
    Vector<EnemyTank> enemyTanks    = new Vector<>();
    int               enemyTankSize = 3;


    public MyPanel() {
        myTank = new MyTank(100, 100);
        myTank.setSpeed(4);
        for (int i = 1; i <= enemyTankSize; i++) {
            EnemyTank enemyTank = new EnemyTank(100 * i, 0);
            enemyTank.setDirection(2);
            enemyTanks.add(enemyTank);
        }


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);
        drawTank(myTank.getX(), myTank.getY(), g, myTank.getDirection(), 0);
        for (EnemyTank enemyTank : enemyTanks) {
            drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirection(), 1);
        }
        if (myTank.shot!=null&&myTank.shot.live){
            g.drawOval(myTank.shot.x,myTank.shot.y,2,2);
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
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
            myTank.setDirection(0);
            myTank.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            myTank.setDirection(1);
            myTank.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
            myTank.setDirection(2);
            myTank.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
            myTank.setDirection(3);
            myTank.moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
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
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.repaint();
        }
    }
}

