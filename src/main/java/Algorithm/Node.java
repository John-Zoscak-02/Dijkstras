package Algorithm;
//
//import java.lang.reflect.Array;
//import java.util.*;
//
//public class Node implements Comparable {
//
//    private Map<String, Double> edges;
//    private String[] orderedIdentifiers;
//    private String identifier;
//
//    public Node(String identifier) {
//        this.identifier = identifier;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Node node = (Node) o;
//        return Objects.equals(edges, node.edges) &&
//                Arrays.equals(orderedIdentifiers, node.orderedIdentifiers) &&
//                Objects.equals(identifier, node.identifier);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = Objects.hash(edges, identifier);
//        result = 31 * result + Arrays.hashCode(orderedIdentifiers);
//        return result;
//    }
//
//    public String getIdentifier() {
//        return identifier;
//    }
//
//    public String[] getConnectedNodeOrderedIdentifiers() {
//        return orderedIdentifiers;
//    }
//
//    public Map<String, Double> getEdges() {
//        return edges;
//    }
//
//    public void setEdges(Map<String, Double> edges) {
//        this.edges = edges;
//
//        orderedIdentifiers = parallelSort(edges.values(), edges.keySet());
//    }
//
//    public String[] parallelSort(Collection<Double> comparable, Set<String> corresponding) {
//        Map<Double, ArrayList<String> > linked = new HashMap<>();
//        Set<Double> doubles = new TreeSet<>();
//
//        Iterator<String> it = corresponding.iterator();
//
//        for ( Double d : comparable ) {
//            doubles.add(d);
//            ArrayList<String> bucket = linked.get(d);
//            Optional<ArrayList<String>> optional = Optional.ofNullable(bucket);
//            if ( !optional.isPresent() ) {
//                bucket = new ArrayList<>();
//            }
//            if ( it.hasNext() ) {
//                bucket.add(it.next());
//            }
//            linked.put(d, bucket);
//        }
//
////        System.out.println(identifier + ": " + linked);
//
//        String[] sorted = new String[corresponding.size()];
//        Double[] dubs = doubles.toArray(new Double[doubles.size()]);
//        Arrays.sort(dubs);
//        int x = 0;
//        for ( Object d : dubs ) {
//            for ( String s : linked.get((Double)d) ) {
////                System.out.println(x + "  ==  " + s);
//                sorted[x] = s;
//                x++;
//            }
//        }
//
//        return sorted;
//    }
//
//    @Override
//    public int compareTo(Object o) {
//        if ( !( o instanceof Node) ) {
//            throw new IllegalArgumentException("Other object is not a Algorithm.Node");
//        }
//        Node other = (Node)o;
//        return other.getIdentifier().compareTo( identifier );
//    }
//
//    @Override
//    public String toString() {
//        return "Algorithm.Node{" +
//                "edges=" + edges +
//                "orderedIdentifiers=" + orderedIdentifiers +
//                ", identifier='" + identifier + '\'' +
//                '}';
//    }
//}

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

    public void setAsBaseTag() {
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
        return Objects.equals(edges, node.edges) && Objects.equals(identifier, node.identifier);
    }

}