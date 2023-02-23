package model;

public class EntrepotInput extends Input {

    private final int maxCapacity;
    private int capacity;

    public EntrepotInput(MachineComponent component, int maxCapacity) {
        super(component);
        this.maxCapacity = maxCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void addCapacity() {
        this.capacity += 1;
    }

    public void removeCapacity() {
        this.capacity -= 1;
    }


    @Override
    public String toString() {
        return "EntrepotInput{" +
                super.toString() +
                ", capacity='" + capacity + '\'' +
                '}';
    }
}
