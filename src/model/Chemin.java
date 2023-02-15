package model;

public class Chemin {

    private int from;
    private int to;

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

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Chemin{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
