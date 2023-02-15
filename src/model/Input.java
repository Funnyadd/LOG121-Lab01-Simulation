package model;

public class Input {

    private MachineComponent component;

    public Input(MachineComponent component) {
        this.component = component;
    }

    public MachineComponent getComponent() {
        return component;
    }

    public void setComponent(MachineComponent component) {
        this.component = component;
    }

    @Override
    public String toString() {
        return component.toString();
    }
}
