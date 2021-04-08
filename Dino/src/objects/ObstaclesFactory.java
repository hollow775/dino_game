package objects;

import java.awt.*;

public class ObstaclesFactory implements Factory{

    @Override
    public Graphics create(Graphics g) {
        return g;
    }
}
