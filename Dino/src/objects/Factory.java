package objects;

import java.awt.*;

public interface Factory {
    default Graphics create(Graphics g){
        return g;
    }
}
