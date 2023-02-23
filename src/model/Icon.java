package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Icon {

    private final String type;
    private final String path;
    private final BufferedImage image;

    public Icon(String type, String path) {
        this.type = type;
        this.path = path;
        
        BufferedImage i;
        try {
            i = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Error while reading one of the images");
            throw new RuntimeException(e);
        }
        this.image = i;
    }

    public BufferedImage getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Icon{" +
                "type='" + type + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
