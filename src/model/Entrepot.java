package model;

public class Entrepot extends Building {

    private Strategy strategy;

    public Entrepot() {
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public String toString() {
        return "Entrepot" + super.toString();
    }
}
