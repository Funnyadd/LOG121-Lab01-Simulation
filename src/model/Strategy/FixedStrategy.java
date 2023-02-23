package model.Strategy;

import model.Building;
import model.Entrepot;

import static simulation.Simulation.productionChain;

public class FixedStrategy implements Strategy {

    private int counter;

    @Override
    public boolean salesAlgorithm() {
        Entrepot entrepot = productionChain.getEntrepot();
        if (entrepot.getInput().getCapacity() >= entrepot.getInput().getMaxCapacity()) {
            counter ++;
            return counter > 10 * productionChain.getSpeedMultiplier();
        }

        counter = 0;
        return false;
    }

    @Override
    public String getIdentifier() {
        return "Fixe";
    }
}
