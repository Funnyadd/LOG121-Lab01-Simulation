package model;

public class Output {

    private String type;

    public Output() {
        this.type = "";
    }

    public Output(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Output{type='" + type + "'}";
    }
}
