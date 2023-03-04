package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MachineComponent {

    private final Point position;
    private final String type;
    private final BufferedImage image;
    private double speed;
    private int destinationId;

    public MachineComponent(String type, Point initialPosition) {
        this.type = type;
        this.position = new Point(initialPosition);
        this.speed = 1;
        this.destinationId = -1;
        this.image = getImageFromType(type);
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Point getPosition() {
        return position;
    }

    public String getType() {
        return type;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }

    @Override
    public String toString() {
        return "MachineComponent{" +
                "type='" + type + "'" +
                ", speed=" + speed +
                ", position=[x:" + position.x + ", y:" + position.y + "]" +
                '}';
    }

    private BufferedImage getImageFromType(String type) {
        final String METAL_PATH = "src\\ressources\\metal.png";
        final String AILE_PATH = "src\\ressources\\aile.png";
        final String MOTEUR_PATH = "src\\ressources\\moteur.png";
        final String AVION_PATH = "src\\ressources\\avion.png";

        String path = METAL_PATH;

        switch (type) {
            case "metal":
                path = METAL_PATH;
                break;
            case "aile":
                path = AILE_PATH;
                break;
            case "moteur":
                path = MOTEUR_PATH;
                break;
            case "avion":
                path = AVION_PATH;
                break;
        }

        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Error while reading one of the images");
            throw new RuntimeException(e);
        }
    }
}
