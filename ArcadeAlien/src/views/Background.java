package views;

import javax.swing.*;
import java.awt.*;


public class Background extends javax.swing.JFrame {
    private BackgroundPanel backgroundPanel;
    
    private Image icon;

    public Background() {
        backgroundPanel = new BackgroundPanel();
        this.setContentPane(backgroundPanel);
        setTitle("ArcadeAlien");
        icon = new ImageIcon("src/resources/Spacecraft.png").getImage();
        this.setIconImage(icon);
    }

    public void run() {
        while (true) {
            backgroundPanel.repaint();
            backgroundPanel.moveMartians();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(800, 600);
            setResizable(false);
            setVisible(true);
        }
    }
}
