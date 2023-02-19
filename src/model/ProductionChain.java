package model;

import java.util.LinkedList;
import java.util.List;

public class ProductionChain {

    private List<Building> buildingList;
    private List<Chemin> cheminList;
    private int speedMultiplier;

    public ProductionChain() {
        this.buildingList = new LinkedList<>();
        this.cheminList = new LinkedList<>();
        this.speedMultiplier = 1;
    }

    public ProductionChain(List<Building> buildings, List<Chemin> cheminList) {
        this.buildingList = buildings;
        this.cheminList = cheminList;
        this.speedMultiplier = 1;
    }

    public List<Building> getBuildingList() {
        return buildingList;
    }

    public void setBuildingList(List<Building> buildings) {
        this.buildingList = buildings;
    }

    public List<Chemin> getCheminList() {
        return cheminList;
    }

    public void setCheminList(List<Chemin> cheminList) {
        this.cheminList = cheminList;
    }

    public int getSpeedMultiplier() {
        return speedMultiplier;
    }

    public void setSpeedMultiplier(int speedMultiplier) {
        this.speedMultiplier = speedMultiplier;
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
}
