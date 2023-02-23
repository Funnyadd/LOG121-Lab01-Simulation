package model;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Building {

    private String type;
    private List<Icon> icon;
    private Output output;
    private int productionInterval;
    private int id;
    private Point coordinates;
    private int intervalCounter;

    public Building() {
        this.type = "";
        this.icon = new LinkedList<>();
        this.output = new Output();
        this.productionInterval = 0;
        this.coordinates = new Point();
        this.id = 0;
        this.intervalCounter = 0;
    }

    public Building(String type, List<Icon> icon, Output output, int productionInterval, Point coordinates, int id) {
        this.type = type;
        this.icon = icon;
        this.output = output;
        this.productionInterval = productionInterval;
        this.coordinates = coordinates;
        this.id = id;
        this.intervalCounter = 0;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Icon> getIcon() {
        return icon;
    }

    public void setIcon(List<Icon> icon) {
        this.icon = icon;
    }

    public Output getOutput() {
        return output;
    }

    public void setOutput(Output output) {
        this.output = output;
    }

    public int getProductionInterval() {
        return productionInterval;
    }

    public void setProductionInterval(int productionInterval) {
        this.productionInterval = productionInterval;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIntervalCounter() {
        return intervalCounter;
    }

    public void resetIntervalCounter() {
        this.intervalCounter = 0;
    }

    public void incrementIntervalCounter() {
        this.intervalCounter += 1;
    }

    @Override
    public String toString() {
        return "{" +
                "type='" + type + '\'' +
                ", id=" + id +
                ", productionInterval=" + productionInterval +
                ", " + output +
                ", coordinates=[x:" + coordinates.x + ", y:" + coordinates.y + "]" +
                ", icons=" + icon +
                '}';
    }
}
