package model;

public class EntrepotInput extends Input {

    private String capacity;

    public EntrepotInput(MachineComponent component, String capacity) {
        super(component);
        this.capacity = capacity;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "EntrepotInput{" +
                super.toString() +
                ", capacity='" + capacity + '\'' +
                '}';
    }
}
