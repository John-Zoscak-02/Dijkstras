package Algorithm;

import java.util.Objects;

public class Entry implements Comparable<Entry>{
    private Node vertex;
    private Node previousNode;
    private int distanceFromBase;

    public Entry(Node vertex) {
        this.vertex = vertex;
        this.previousNode = null;
        this.distanceFromBase = Integer.MAX_VALUE;
    }

    public int getDistanceFromBase() {
        return distanceFromBase;
    }

    public void setDistanceFromBase(int distanceFromBase) {
        this.distanceFromBase = distanceFromBase;
    }

    public Node getVertex() {
        return vertex;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return distanceFromBase == entry.distanceFromBase && Objects.equals(vertex, entry.vertex) && Objects.equals(previousNode, entry.previousNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertex, previousNode, distanceFromBase);
    }

    @Override
    public int compareTo(Entry o) {
        if (vertex.isBase()) {
            return -1;
        }
        else if (o.getVertex().isBase()) {
            return 1;
        }
        for (Node node1 : Dijkstra.visited) {
            if ( node1.hasNeighbor(o.getVertex()) ) {
                for (Node node2 : Dijkstra.visited) {
                    if ( node2.hasNeighbor(getVertex()) ) {
                        return distanceFromBase - o.distanceFromBase;
                    }
                    else {
                        return 1;
                    }
                }
            }
        }
        for (Node node3 : Dijkstra.visited) {
            if ( node3.hasNeighbor(getVertex()) ) {
                return -1;
            }
        }
        return distanceFromBase - o.distanceFromBase;
    }
}
