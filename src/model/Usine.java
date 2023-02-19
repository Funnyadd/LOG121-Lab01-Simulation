package model;

import java.util.LinkedList;
import java.util.List;

public class Usine extends Building implements Observer {

    private List<UsineInput> inputList;

    public Usine(Building b) {
        super(b.getType(), b.getIcon(), b.getOutput(), b.getProductionInterval(), b.getCoordinates(), b.getId());
        this.inputList = new LinkedList<>();
    }

    public List<UsineInput> getInputList() {
        return inputList;
    }

    public void setInputList(List<UsineInput> inputList) {
        this.inputList = inputList;
    }

    @Override
    public String toString() {
        return "Usine{" +
                "inputList=" + inputList +
                '}';
    }

    @Override
    public void update() {

    }
}
