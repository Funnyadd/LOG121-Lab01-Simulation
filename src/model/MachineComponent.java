package model;

import java.awt.*;

public class MachineComponent extends Icon {

    private int speed;
    private Point position;

    public MachineComponent(String type, Point initialPosition) {
        super(type);
        super.setPath(getPathFromType(type));
        this.position = new Point(initialPosition);
        this.speed = 1;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "MachineComponent{" +
                "type='" + super.getType() + "'" +
                ", path='" + super.getPath() + "'" +
                ", speed=" + speed +
                ", position=[x:" + position.x + ", y:" + position.y + "]" +
                '}';
    }

    private String getPathFromType(String type) {
        final String METAL_PATH = "src\\ressources\\metal.png";
        final String AILE_PATH = "src\\ressources\\aile.png";
        final String MOTEUR_PATH = "src\\ressources\\moteur.png";
        final String AVION_PATH = "src\\ressources\\avion.png";

        switch (type) {
            case "metal":
                return METAL_PATH;
            case "aile":
                 return AILE_PATH;
            case "moteur":
                return MOTEUR_PATH;
            case "avion":
                return AVION_PATH;
        }
        return METAL_PATH;
    }
}
