package models;

import javax.swing.*;
import java.awt.*;

import Data.Data;

public class Martians {
    private int x, y;
    private int speed;
    private Image imagenMartian;
    private int width, height;

    public Martians(int xInitial, int yInitial, int speed, int width, int height) {
        this.x = xInitial;
        this.y = yInitial;
        this.speed = speed;
        this.width = width;
        this.height = height;
        ImageIcon Martian = new ImageIcon("src/resources/Martian.png");
        imagenMartian = Martian.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    public boolean move() {
        x -= speed;
        if (x < -10) {
            return false;
        } else {
            return true;
        }
    }

    public boolean intersects(Bullet bullet) {
        Rectangle martianRect = new Rectangle(x, y, width, height);
        Rectangle bulletRect = new Rectangle(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
        return martianRect.intersects(bulletRect);
    }

    public void draw(Graphics g) {
        g.drawImage(imagenMartian, x, y, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
