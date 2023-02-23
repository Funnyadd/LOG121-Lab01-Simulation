package model.Strategy;

public class DefaultStrategy implements Strategy {

    @Override
    public boolean salesAlgorithm() {
        return false;
    }

    @Override
    public String getIdentifier() {
        return "Par défault";
    }
}
