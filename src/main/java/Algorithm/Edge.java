package Algorithm;

public class Edge {
    private int distance;
    private Node first;
    private Node second;

    public Edge(int distance, Node first, Node second) {
        this.distance = distance;
        this.first = first;
        this.second = second;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Node getFirst() {
        return first;
    }

    public void setFirst(Node first) {
        this.first = first;
    }

    public Node getSecond() {
        return second;
    }

    public void setSecond(Node second) {
        this.second = second;
    }
}
