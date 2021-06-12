package Algorithm;


import java.util.Objects;

public class Entry implements Comparable<Entry>{
    private Node vertex;
    private Node previousNode;
    private int distanceFromBase;

    public Entry(Node vertex) {
        this.vertex = vertex;
        this.previousNode = null;
        this.distanceFromBase = Integer.MAX_VALUE-10;
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
        return getVertex().getIdentifier().compareTo(o.getVertex().getIdentifier());
    }

    @Override
    public String toString() {
        return "   " + vertex + "     " + distanceFromBase + "            " + previousNode;
    }
}
