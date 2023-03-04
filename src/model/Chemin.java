package model;

public class Chemin {

    private final int from;
    private final int to;

    public Chemin(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public Chemin() {
        this.from = 0;
        this.to = 0;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "Chemin{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
