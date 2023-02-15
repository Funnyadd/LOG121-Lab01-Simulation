package model;

import java.util.LinkedList;
import java.util.List;

public class ProductionChain {

    private List<Building> buildingList;
    private List<Chemin> cheminList;

    public ProductionChain() {
        this.buildingList = new LinkedList<>();
        this.cheminList = new LinkedList<>();
    }

    public ProductionChain(List<Building> buildings, List<Chemin> cheminList) {
        this.buildingList = buildings;
        this.cheminList = cheminList;
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
