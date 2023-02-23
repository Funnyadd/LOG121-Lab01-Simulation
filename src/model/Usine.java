package model;

import java.util.LinkedList;
import java.util.List;

public class Usine extends Building implements Observer {

    private List<UsineInput> inputList;
    private boolean isTurnedOn;

    public Usine(Building b) {
        super(b.getType(), b.getIcon(), b.getOutput(), b.getProductionInterval(), b.getCoordinates(), b.getId());
        this.inputList = new LinkedList<>();
        this.isTurnedOn = true;
    }

    public List<UsineInput> getInputList() {
        return inputList;
    }

    public void setInputList(List<UsineInput> inputList) {
        this.inputList = inputList;
    }

    public boolean isTurnedOn() {
        return isTurnedOn;
    }

    @Override
    public String toString() {
        return "Usine{" +
                super.toString() +
                "inputList=" + inputList +
                '}';
    }

    @Override
    public void turnOnOff() {
        this.isTurnedOn = !this.isTurnedOn;
    }
}
