package model;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Building {

    private String type;
    private List<Icon> icon;
    private List<Input> input;
    private Output output;
    private int productionInterval;
    private int id;
    private Point coordinates;

    public Building() {
        this.type = "";
        this.icon = new LinkedList<>();
        this.input = new LinkedList<>();
        this.output = new Output();
        this.productionInterval = 0;
        this.coordinates = new Point();
        this.id = 0;
    }

    public Building(String type, List<Icon> icon, List<Input> input, Output output, int productionInterval, Point coordinates, int id) {
        this.type = type;
        this.icon = icon;
        this.input = input;
        this.output = output;
        this.productionInterval = productionInterval;
        this.coordinates = coordinates;
        this.id = id;
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

    public List<Input> getInput() {
        return input;
    }

    public void setInput(List<Input> input) {
        this.input = input;
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
}
