package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Icon {

    private String type;
    private String path;
    private BufferedImage image;

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

    public Icon(String type) {
        this.type = type;
        this.path = "";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
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

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Icon{" +
                "type='" + type + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
