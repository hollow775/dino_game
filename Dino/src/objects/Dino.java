package objects;

import getImage.GetImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Dino {
    static int dinoBaseY, dinoTopY, dinoStartX, dinoEndX;
    static int dinoTop, dinoBottom, topPoint;

    static boolean topPointReached;
    static int jumpFactor = 20;

    public static final int RUN = 1, JUMPING = 2, DIE = 3;

    private static int state;

    static BufferedImage image;
    BufferedImage deadDino;

    public Dino() {
        image = new GetImage().getImage("../Imagens/Dino-stand.png");
        deadDino = new GetImage().getImage("../Imagens/Dino-dead.png");

        dinoBaseY = Ground.GROUND_Y + 5;
        dinoTopY = Ground.GROUND_Y - image.getHeight() + 5;
        dinoStartX = 100;
        dinoEndX = dinoStartX + image.getWidth();
        topPoint = dinoTopY - 120;

        state = 1;
    }

    public void create(Graphics g) {
        dinoBottom = dinoTop + image.getHeight();

        // g.drawRect(getDino().x, getDino().y, getDino().width, getDino().height);

        switch(state) {

            case RUN:
                g.drawImage(image, dinoStartX, dinoTopY, null);
                break;

            case JUMPING:
                if(dinoTop > topPoint && !topPointReached) {
                    g.drawImage(image, dinoStartX, dinoTop -= jumpFactor, null);
                    break;
                }
                if(dinoTop >= topPoint && !topPointReached) {
                    topPointReached = true;
                    g.drawImage(image, dinoStartX, dinoTop += jumpFactor, null);
                    break;
                }
                if(dinoTop > topPoint && topPointReached) {
                    if(dinoTopY == dinoTop && topPointReached) {
                        state = RUN;
                        topPointReached = false;
                        break;
                    }
                    g.drawImage(image, dinoStartX, dinoTop += jumpFactor, null);
                    break;
                }
            case DIE:
                g.drawImage(deadDino, dinoStartX, dinoTop, null);
                break;
        }
    }

    public void die() {
        state = DIE;
    }

    public static Rectangle getDino() {
        Rectangle dino = new Rectangle();
        dino.x = dinoStartX;

        if(state == JUMPING && !topPointReached) dino.y = dinoTop - jumpFactor;
        else if(state == JUMPING && topPointReached) dino.y = dinoTop + jumpFactor;
        else if(state != JUMPING) dino.y = dinoTop;

        dino.width = image.getWidth();
        dino.height = image.getHeight();

        return dino;
    }

    public void startRunning() {
        dinoTop = dinoTopY;
        state = RUN;
    }

    public void jump() {
        dinoTop = dinoTopY;
        topPointReached = false;
        state = JUMPING;
    }

    private class DinoImages {

    }

}