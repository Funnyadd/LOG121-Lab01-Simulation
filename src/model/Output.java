package model;

import java.util.LinkedList;
import java.util.List;

public class Output {

    private final String type;
    private final List<MachineComponent> machineComponents = new LinkedList<>();

    public Output() {
        this.type = "";
    }

    public Output(String type) {
        this.type = type;

    }

    public String getType() {
        return type;
    }

    public List<MachineComponent> getMachineComponents() {
        return machineComponents;
    }

    public void addMachineComponents(MachineComponent machineComponent) {
        machineComponents.add(machineComponent);
    }

    @Override
    public String toString() {
        return "Output{" +
                "type='" + type + '\'' +
                ", machineComponents=" + machineComponents +
                '}';
    }
}
