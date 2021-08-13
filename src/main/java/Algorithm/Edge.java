package Algorithm;

import java.util.Objects;

public class Edge implements Comparable<Edge>{
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

    public int compareTo(Edge o) {
        if (this == o) return 0;
        Edge edge = o;
        if ((Objects.equals(first, edge.first) && Objects.equals(second, edge.second)) || (Objects.equals(first, edge.second) && Objects.equals(second, edge.first))) {
            return 0;
        }
        if (distance == o.distance) {
            return 1;
        }
        return distance - o.distance;
    }

    public Node getSecond() {
        return second;
    }

    public void setSecond(Node second) {
        this.second = second;
    }
}
