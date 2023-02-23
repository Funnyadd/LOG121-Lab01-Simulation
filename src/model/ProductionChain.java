package model;

import java.util.LinkedList;
import java.util.List;

import static simulation.Simulation.productionChain;

public class ProductionChain {

    private String filePath;
    private List<Building> buildingList;
    private List<Chemin> cheminList;
    private double speedMultiplier = 1;
    private double optimisationMultiplier = 1;
    private boolean isOptimisation = true;
    private boolean isOn = true;

    public ProductionChain() {
        this.buildingList = new LinkedList<>();
        this.cheminList = new LinkedList<>();
    }

    public ProductionChain(List<Building> buildings, List<Chemin> cheminList, String filePath) {
        this.buildingList = buildings;
        this.cheminList = cheminList;
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public List<Building> getBuildingList() {
        return buildingList;
    }

    public List<Chemin> getCheminList() {
        return cheminList;
    }

    public double getSpeedMultiplier() {
        return speedMultiplier;
    }

    public void setSpeedMultiplier(double speedMultiplier) {
        this.speedMultiplier = speedMultiplier;
    }

    public double getOptimisationMultiplier() {
        return optimisationMultiplier;
    }

    public void setOptimisationMultiplier(double optimisationMultiplier) {
        this.optimisationMultiplier = optimisationMultiplier;
    }

    public boolean isOptimisation() {
        return isOptimisation;
    }

    public void setOptimisation(boolean optimisation) {
        isOptimisation = optimisation;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setIsOn(boolean on) {
        isOn = on;
    }

    public Building getBuildingById(int id) {
        for (Building b : buildingList) {
            if (b.getId() == id) return b;
        }
        return new Building();
    }

    public Chemin getCheminByIdFrom(int id) {
        for (Chemin c : cheminList) {
            if (c.getFrom() == id) return c;
        }
        return new Chemin();
    }

    public Entrepot getEntrepot() {
        for (Building b : productionChain.getBuildingList()) {
            if (b instanceof Entrepot) {
                return ((Entrepot) b);
            }
        }
        return null;
    }
}
