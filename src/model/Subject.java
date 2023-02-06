package model;

import java.util.LinkedList;
import java.util.List;

interface Subject {
    List<Observer> listObservers = new LinkedList<>();

    default void attach(Observer o) {
        listObservers.add(o);
    }

    default void detach(Observer o) {
        listObservers.remove(o);
    }

    default void notifyObservers() {
        for (Observer obv : listObservers) {
            obv.update();
        }
    }
}
