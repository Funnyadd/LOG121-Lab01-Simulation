package model.Strategy;

import static simulation.Simulation.productionChain;

public class RandomStrategy implements Strategy {

    @Override
    public boolean salesAlgorithm() {
        return (int) (Math.random() * 2000 / productionChain.getSpeedMultiplier()) == (int) (1000 / productionChain.getSpeedMultiplier());
    }

    @Override
    public String getIdentifier() {
        return "Aléatoire";
    }
}
