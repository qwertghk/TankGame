package com.jin.TankGame;

public class Tank {
    private int     x;
    private int     y;
    private int     direction;
    private int     speed;
    private Boolean life = true;

    public Boolean getLife() {
        return life;
    }

    public void setLife(Boolean life) {
        this.life = life;
    }

    //上左下右移动
    public void moveUp() {
        if (y > 0) y -= speed;
    }

    public void moveLeft() {
        if (x > 0) x -= speed;
    }

    public void moveDown() {
        if (y + 60 < 750) y += speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void moveRight() {
        if (x + 60 < 1000) x += speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
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

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
