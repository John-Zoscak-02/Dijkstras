package Exec;

import Algorithm.Edge;
import Algorithm.Node;

import java.util.ArrayList;
import java.util.List;

public class Ref {
    private List<String> neighbors;

    public List<Integer> getDistances() {
        return distances;
    }

    public void setDistances(List<Integer> distances) {
        this.distances = distances;
    }

    private List<Integer> distances;
    private String identifier;
    private boolean baseTag;

    public Ref(Node node) {
        neighbors = new ArrayList<>();
        distances = new ArrayList<>();
        for (Edge edge : node.getEdges()) {
            neighbors.add(edge.getSecond().getIdentifier());
            distances.add(edge.getDistance());
        }
        identifier = node.getIdentifier();
        baseTag = node.isBase();
    }

    public List<String> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<String> neighbors) {
        this.neighbors = neighbors;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public boolean isBaseTag() {
        return baseTag;
    }

    public void setBaseTag(boolean baseTag) {
        this.baseTag = baseTag;
    }

}