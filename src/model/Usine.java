package model;

public class Usine extends Building implements Subject {
    public Usine(Building b) {
        super(b.getType(), b.getIcon(), b.getInput(), b.getOutput(), b.getProductionInterval(), b.getCoordinates(), b.getId());
    }

    @Override
    public String toString() {
        return "Usine" + super.toString();
    }
}
