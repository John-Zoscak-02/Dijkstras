import java.util.*;

public class WeightedGraph {
    private Map<String, Node> nodes;

    public WeightedGraph(Collection<Node> nodes) {
        this.nodes = new HashMap<>();
        for ( Node n : nodes ) {
            this.nodes.put(n.getIdentifier(), n);
        }
    }

    public Node[] getNodes() {
        return nodes.values().toArray( new Node[nodes.size()] );
    }

    public Node get( String identifier ) {
        return nodes.get( identifier );
    }

    public boolean set( String identifier, Node n ) {
        if ( nodes.containsKey(identifier) ) {
            nodes.put(identifier, n);
            return true;
        }
        return false;
    }


}
