package model.Strategy;

import model.Building;
import model.Entrepot;

import static simulation.Simulation.productionChain;

public class FixedStrategy implements Strategy {

    private int counter;

    @Override
    public boolean salesAlgorithm() {
        for (Building b : productionChain.getBuildingList()) {
            if (b instanceof Entrepot && ((Entrepot) b).getInput().getCapacity() >= ((Entrepot) b).getInput().getMaxCapacity()) {
                counter ++;
                return counter > 10 * productionChain.getSpeedMultiplier();
            }
        }
        counter = 0;
        return false;
    }

    @Override
    public String getIdentifier() {
        return "fixe";
    }
}
