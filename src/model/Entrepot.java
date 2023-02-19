package model;

public class Entrepot extends Building {

    private Strategy strategy;
    private EntrepotInput input;

    public Entrepot(Building b) {
        super(b.getType(), b.getIcon(), b.getOutput(), b.getProductionInterval(), b.getCoordinates(), b.getId());
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public EntrepotInput getInput() {
        return input;
    }

    public void setInput(EntrepotInput input) {
        this.input = input;
    }

    @Override
    public String toString() {
        return "Entrepot{" +
                "strategy=" + strategy +
                ", input=" + input +
                '}';
    }
}
