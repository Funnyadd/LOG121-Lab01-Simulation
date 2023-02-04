package model;

public class Building {

    private String type;
    private String icon;
    private Input input;
    private Output output;
    private int productionInterval;

    public Building(String type, String icon, Input input, Output output, int productionInterval) {
        this.type = type;
        this.icon = icon;
        this.input = input;
        this.output = output;
        this.productionInterval = productionInterval;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
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
}
