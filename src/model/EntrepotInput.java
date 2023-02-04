package model;

public class EntrepotInput extends Input {

    private String capacity;

    public EntrepotInput(String material, String capacity) {
        super(material);
        this.capacity = capacity;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
}
