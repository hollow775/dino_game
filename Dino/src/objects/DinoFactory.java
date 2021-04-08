package objects;

import java.awt.*;

public class DinoFactory implements Factory{
    public Graphics create(Graphics g){
        Dino dino = new Dino();

        final int RUN = 1, JUMPING = 2, DIE = 3;
        int state = RUN;

        dino.dinoBottom = dino.dinoTop + dino.image.getHeight();

        // g.drawRect(getDino().x, getDino().y, getDino().width, getDino().height);

        switch(state) {

            case RUN:
                g.drawImage(dino.image, dino.dinoStartX, dino.dinoTopY, null);
                break;

            case JUMPING:
                if(dino.dinoTop > dino.topPoint && !dino.topPointReached) {
                    g.drawImage(dino.image, dino.dinoStartX, dino.dinoTop -= dino.jumpFactor, null);
                    break;
                }
                if(dino.dinoTop >= dino.topPoint && !dino.topPointReached) {
                    dino.topPointReached = true;
                    g.drawImage(dino.image, dino.dinoStartX, dino.dinoTop += dino.jumpFactor, null);
                    break;
                }
                if(dino.dinoTop > dino.topPoint && dino.topPointReached) {
                    if(dino.dinoTopY == dino.dinoTop && dino.topPointReached) {
                        state = RUN;
                        dino.topPointReached = false;
                        break;
                    }
                    g.drawImage(dino.image, dino.dinoStartX, dino.dinoTop += dino.jumpFactor, null);
                    break;
                }
            case DIE:
                g.drawImage(dino.deadDino, dino.dinoStartX, dino.dinoTop, null);
                break;
        }
        return g;
    }
}
