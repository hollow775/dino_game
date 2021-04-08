package getImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GetImage {

    public BufferedImage getImage(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

}
