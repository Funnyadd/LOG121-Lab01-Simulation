package model;

import java.util.LinkedList;
import java.util.List;

public class Entrepot extends Building {

    private Strategy strategy;
    private EntrepotInput input;
    private boolean isFull;

    private List<Observer> listObservers = new LinkedList<>();

    public Entrepot(Building b) {
        super(b.getType(), b.getIcon(), b.getOutput(), b.getProductionInterval(), b.getCoordinates(), b.getId());
        this.isFull = false;
        this.strategy = new DefaultStrategy();
    }

    public Entrepot() {
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

    public void sellPlane() {
        if (strategy.salesAlgorithm()) {
            input.removeCapacity();
        }
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    @Override
    public String toString() {
        return "Entrepot{" +
                "strategy=" + strategy +
                ", input=" + input +
                '}';
    }

    // Subject for observer part of the code
    public void attach(Observer o) {
        this.listObservers.add(o);
    }

    public void detach(Observer o) {
        this.listObservers.remove(o);
    }

    public void notifyObservers() {
        for (Observer obv : listObservers) {
            obv.update();
        }
    }
}
