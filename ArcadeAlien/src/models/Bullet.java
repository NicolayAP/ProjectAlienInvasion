package models;

import javax.swing.*;
import java.awt.*;


public class Bullet {
    private int x, y;
    private int speed;
    private Image imagenBullet;
    private int width, height;

    public Bullet(int xInitial, int yInitial, int speed,int width, int height ) {
        this.x = xInitial;
        this.y = yInitial;
        this.speed = speed;
        this.width = width;
        this.height = height;
        ImageIcon bulletImage = new ImageIcon("src/resources/Bullet.png");
        imagenBullet = bulletImage.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

    }

    public void move() {
        y -= speed;
    }

    public void draw(Graphics g) {
        g.drawImage(imagenBullet, x, y, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

