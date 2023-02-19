package model;

public class DefaultStrategy implements Strategy {

    @Override
    public boolean salesAlgorithm() {
        return false;
    }

    @Override
    public String getIdentifier() {
        return "par défault";
    }
}
