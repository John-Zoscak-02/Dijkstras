package Algorithm;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Node {
    private List<Edge> edges;
    private String identifier;
    private boolean baseTag;

    public Node(String identifier) {
        this.identifier = identifier;
        edges = new ArrayList<>();
        baseTag = false;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public Edge path(Node other, int distance) {
        Edge edge = new Edge(distance, this, other);
        edges.add(edge);
        return edge;
    }

    public void setAsBase() {
        baseTag = true;
    }

    public boolean isBase() {
        return baseTag;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public boolean hasNeighbor(Node o) {
        for (Edge edge : edges) {
            if (o.equals(edge.getSecond())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(identifier, node.identifier);
    }

    @Override
    public String toString() {
        return identifier;
    }

}