package model.Strategy;

public class RandomStrategy implements Strategy {

    @Override
    public boolean salesAlgorithm() {
        return (int) (Math.random() * 100) == 50;
    }

    @Override
    public String getIdentifier() {
        return "aléatoire";
    }
}
