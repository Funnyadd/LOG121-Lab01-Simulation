package model;

import java.util.LinkedList;
import java.util.List;

public class Output {

    private String type;

    private List<MachineComponent> machineComponents;

    public Output() {
        this.type = "";
        this.machineComponents = new LinkedList<>();
    }

    public Output(String type) {
        this.type = type;
        this.machineComponents = new LinkedList<>();

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<MachineComponent> getMachineComponents() {
        return machineComponents;
    }

    public void addMachineComponents(MachineComponent machineComponent) {
        machineComponents.add(machineComponent);
    }

    public void removeMachineComponents(MachineComponent machineComponent) {
        machineComponents.remove(machineComponent);
    }

    public void setMachineComponents(List<MachineComponent> machineComponents) {
        this.machineComponents = machineComponents;
    }

    @Override
    public String toString() {
        return "Output{" +
                "type='" + type + '\'' +
                ", machineComponents=" + machineComponents +
                '}';
    }
}
