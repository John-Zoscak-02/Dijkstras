import java.util.*;

public class WeightedGraph {
    private Map<String, Node> nodes;
    private Node[] nodeArr;

    public WeightedGraph(Collection<Node> nodes) {
        this.nodes = new HashMap<>();
        nodeArr = new Node[nodes.size()];

        int i = 0;
        for ( Node n : nodes ) {
            this.nodes.put(n.getIdentifier(), n);
            nodeArr[i] = n;
            i++;

        }
    }

    public Node[] getNodes() {
        return nodeArr;
    }

    public Node get( String identifier ) {
        return nodes.get( identifier );
    }

    public int size() {
        return nodes.size();
    }

}
