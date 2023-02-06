package model;

public class Entrepot extends Building implements Observer {

    private Strategy strategy;

    public Entrepot(Building b) {
        super(b.getType(), b.getIcon(), b.getInput(), b.getOutput(), b.getProductionInterval(), b.getCoordinates(), b.getId());
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

    @Override
    public void update() {

    }
}
