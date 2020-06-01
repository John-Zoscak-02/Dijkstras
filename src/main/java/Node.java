import java.util.Map;
import java.util.Objects;

public class Node implements Comparable {

    private Map<Double, String> connections;
    private String identifier;

    public Node(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(connections, node.connections) &&
                Objects.equals(identifier, node.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connections, identifier);
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Map<Double, String> getConnections() {
        return connections;
    }

    public void setConnections(Map<Double, String> connections) {
        this.connections = connections;
    }

    @Override
    public int compareTo(Object o) {
        if ( !( o instanceof Node) ) {
            throw new IllegalArgumentException("Other object is not a Node");
        }
        Node other = (Node)o;
        return other.getIdentifier().compareTo( identifier );
    }

    @Override
    public String toString() {
        return "Node{" +
                "connections=" + connections +
                ", identifier='" + identifier + '\'' +
                '}';
    }
}