package model;

import java.util.LinkedList;
import java.util.List;

public class ProductionChain {

    private List<Building> buildings;
    private List<Chemin> cheminList;

    public ProductionChain() {
        this.buildings = new LinkedList<>();
        this.cheminList = new LinkedList<>();
    }

    public ProductionChain(List<Building> buildings, List<Chemin> cheminList) {
        this.buildings = buildings;
        this.cheminList = cheminList;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    public List<Chemin> getCheminList() {
        return cheminList;
    }

    public void setCheminList(List<Chemin> cheminList) {
        this.cheminList = cheminList;
    }
}
