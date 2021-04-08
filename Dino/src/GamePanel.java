import objects.Dino;
import objects.Ground;
import objects.Obstacles;
import getImage.GetImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

class GamePanel extends JPanel implements KeyListener, Runnable {

    public static int WIDTH;
    public static int HEIGHT;
    private Thread animator;

    private boolean running = false;
    private boolean gameOver = false;

    Ground ground;
    Dino dino;
    Obstacles obstacles;

    private int score;
    BufferedImage sun;

    public GamePanel() {
        WIDTH = UserInterface.WIDTH;
        HEIGHT = UserInterface.HEIGHT;

        ground = new Ground(HEIGHT);
        dino = new Dino();
        obstacles = new Obstacles((int)(WIDTH * 1.5));

        score = 0;

        setSize(WIDTH, HEIGHT);
        setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        sun = new GetImage().getImage("../Imagens/Sun.png");
        g.setFont(new Font("Courier New", Font.BOLD, 25));
        g.drawString(Integer.toString(score), getWidth()/2 - 5, 100);
        g.drawImage(sun, getWidth()/9, 50, null);
        ground.create(g);
        dino.create(g);
        obstacles.create(g);
    }

    public void run() {
        running = true;

        while(running) {
            updateGame();
            repaint();
            try {
                Thread.sleep(50);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateGame() {
        score += 1;

        ground.update();
        obstacles.update();

        if(obstacles.hasCollided()) {
            dino.die();
            repaint();
            running = false;
            gameOver = true;
        }
    }

    public void reset() {
        score = 0;
        obstacles.resume();
        gameOver = false;
    }

    public void keyTyped(KeyEvent e) {
        // System.out.println(e);
        if(e.getKeyChar() == ' ') {
            if(gameOver) reset();
            if (animator == null || !running) {
                System.out.println("Game starts");
                animator = new Thread(this);
                animator.start();
                dino.startRunning();
            } else {
                dino.jump();
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        // System.out.println("keyPressed: "+e);
    }

    public void keyReleased(KeyEvent e) {
        // System.out.println("keyReleased: "+e);
    }
}