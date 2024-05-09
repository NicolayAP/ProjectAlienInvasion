package models;

import views.BackgroundPanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class MyKeyListener extends KeyAdapter {

    private BackgroundPanel backgroundPanel;
    private Canyon canyon;

    public MyKeyListener(BackgroundPanel backgroundPanel) {
        this.backgroundPanel = backgroundPanel;
        canyon = backgroundPanel.getCanyon();

    }
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_LEFT){
            backgroundPanel.getCanyon().moveLeft();
        }else if(keyCode == KeyEvent.VK_RIGHT){
            backgroundPanel.getCanyon().moveRight();
        }else if(keyCode == canyon.getShootKey()){
            backgroundPanel.getCanyon().shoot();
        }

        backgroundPanel.repaint();
    }


}
