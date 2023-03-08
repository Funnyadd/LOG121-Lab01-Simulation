package model;

import model.Strategy.DefaultStrategy;
import model.Strategy.Strategy;

import java.util.LinkedList;
import java.util.List;

public class Entrepot extends Building {

    private final List<Observer> listObservers = new LinkedList<>();

    private Strategy strategy;
    private EntrepotInput input;
    private boolean isFull;

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
        if (strategy.salesAlgorithm() && input.getCapacity() > 0) {
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

    public void turnUsinesOnOff() {
        boolean state = true;
        for (Observer obv : listObservers) {
            state = obv.turnOnOff();
        }
        setFull(state);
    }
}
