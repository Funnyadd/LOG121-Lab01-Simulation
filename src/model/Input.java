package model;

public class Input {

    private Component component;

    public Input(Component component) {
        this.component = component;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    @Override
    public String toString() {
        return component.toString();
    }
}
