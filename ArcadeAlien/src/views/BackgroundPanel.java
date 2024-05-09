package views;

import models.Bullet;
import models.Canyon;
import models.Martians;
import models.MyKeyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;


public class BackgroundPanel extends JPanel {
    private Image background;
    private Canyon canyon;
    private ArrayList<Martians> martians;
    private ArrayList<Bullet> bullets;
    private Timer timer;
    private int gameSeconds = 0;
    private JLabel timeLabel;
    private JLabel martianCountLabel;
    private JLabel eliminatedMartianCountLabel;


    public Canyon getCanyon() {
        return canyon;
    }

    private void generateRandomMartians() {
        Random rand = new Random();
        Timer martianTimer = new Timer(1000, e -> {
            int x = rand.nextInt(getWidth());
            int y = rand.nextInt(getHeight() / 2);
            int speed = rand.nextInt(5) + 3;
            int size = rand.nextInt(30) + 30;
            martians.add(new Martians(x, y, speed, size, size));
            martianCountLabel.setText("Martians: " + martians.size());
        });
        martianTimer.start();
    }

    private void startGameLoop() {
        Timer gameLoopTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGame();
                repaint();
            }
        });
        gameLoopTimer.start();
    }

    private void updateGame() {
        canyon.moveBullets();

        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.move();
            if (bullet.getY() < 0 || bullet.getX() < 0 || bullet.getX() > getWidth()) {
                bullets.remove(i);
                i--;
            } else {
                for (int j = 0; j < martians.size(); j++) {
                    Martians martian = martians.get(j);
                    if (martian.intersects(bullet)) {
                        bullets.remove(i);
                        martians.remove(j);
                        eliminatedMartianCountLabel.setText("Eliminated: " + (Integer.parseInt(eliminatedMartianCountLabel.getText().split(":")[1].trim()) + 1));
                        i--;
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < martians.size(); i++) {
            Martians martian = martians.get(i);
            if (martian.getX() < 0 || martian.getX() > getWidth()) {
                martians.remove(i);
                i--;
            }
        }
        repaint();
    }


    private void updateTimeLabel() {
        int hours = gameSeconds / 3600;
        int minutes = (gameSeconds % 3600) / 60;
        int seconds = gameSeconds % 60;
        timeLabel.setText(String.format("Time: %02d:%02d:%02d", hours, minutes, seconds));
    }

    public void editsLabels() {
        JPanel infoPanel = new JPanel(new GridLayout(1, 3));
        timeLabel = new JLabel("Time: 00:00:00");
        timeLabel.setForeground(Color.WHITE);
        martianCountLabel = new JLabel("Martians: 0");
        martianCountLabel.setForeground(Color.WHITE);
        eliminatedMartianCountLabel = new JLabel("Eliminated: 0");
        eliminatedMartianCountLabel.setForeground(Color.WHITE);
        infoPanel.add(timeLabel);
        infoPanel.add(martianCountLabel);
        infoPanel.add(eliminatedMartianCountLabel);
        infoPanel.setOpaque(false);
        add(infoPanel, BorderLayout.NORTH);
        timeLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        martianCountLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        this.setFocusable(true);
    }

    public BackgroundPanel() {
        martians = new ArrayList<>();
        bullets = new ArrayList<>();
        background = new ImageIcon("src/resources/Background.jpg").getImage();
        setOpaque(false);
        canyon = new Canyon(400, 500, 10, 60, 60, this);
        this.requestFocusInWindow();
        this.addKeyListener(new MyKeyListener(this));
        editsLabels();
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameSeconds++;
                updateTimeLabel();
            }
        });
        timer.start();
        generateRandomMartians();
        startGameLoop();

    }

    public void moveMartians() {
        for (Martians m : martians) {
            if (m.move() == false) {
                martians.remove(m);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        canyon.draw(g);
        for (Martians martian : martians) {
            martian.draw(g);
        }
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
}

