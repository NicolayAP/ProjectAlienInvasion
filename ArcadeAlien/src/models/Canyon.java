package models;

import views.BackgroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Canyon {

    private int x, y;
    private int speed;
    private Image imageSpacecraft;
    private int width, height;
    private ArrayList<Bullet> bullets;
    private ArrayList<Martians> martians;
    private BackgroundPanel backgroundPanel;
    private int shootKey;


    public Canyon(int xInitial, int yInitial, int speed, int width, int height, BackgroundPanel backgroundPanel) {
        this.x = xInitial;
        this.y = yInitial;
        this.speed = speed;
        this.width = width;
        this.height = height;
        this.bullets = new ArrayList<>();
        this.martians = new ArrayList<>();
        this.backgroundPanel = backgroundPanel;
        enterKeyShoot();
        ImageIcon imageShip = new ImageIcon("src/resources/Spacecraft.png");
        imageSpacecraft = imageShip.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);

    }

    public void enterKeyShoot(){
        String input = JOptionPane.showInputDialog("Enter the word for shoot between A-Z:");
        try {
            shootKey = KeyEvent.getExtendedKeyCodeForChar(input.charAt(0));

        } catch (Exception e) {
            shootKey = KeyEvent.VK_SPACE;
        }
    }

    public void shoot() {
        Bullet recentBullet = new Bullet(x + imageSpacecraft.getWidth(null) / 3, y, 10, 20, 20);
        backgroundPanel.getBullets().add(recentBullet);
    }

    public void moveBullets() {
        for (Bullet bullet : bullets) {
            bullet.move();
        }
    }

    public void moveLeft() {
        x -= speed;
    }

    public void moveRight() {
        x += speed;
    }

    public void draw(Graphics g) {
        if(x<0){
            x= 800;
        }else if(x>800){
            x=0;
        }
        g.drawImage(imageSpacecraft, x, y, null);
    }
    public void keyPressed(int keyCode) {
        if (keyCode == shootKey) {
            shoot();
        }
    }

    public int getShootKey() {
        return shootKey;
    }
}
