package model;

import java.util.LinkedList;
import java.util.List;

public abstract class Subject {
    List<Observer> listObservers = new LinkedList<>();

    public void attach(Observer o) {
        listObservers.add(o);
    }

    public void detach(Observer o) {
        listObservers.remove(o);
    }

    public void notifyObservers() {
        for (Observer obv : listObservers) {
            obv.update();
        }
    }
}
