package com.jin.TankGame;

import javax.swing.*;
import java.util.Vector;

public class TankGame01 extends JFrame {

    MyPanel mp;
    public static void main(String[] args) {
        TankGame01 tankGame01 = new TankGame01();

    }

    public TankGame01() {
        mp =new MyPanel();
        this.add(mp);
        this.setSize(1000,750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
