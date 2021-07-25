package Algorithm;

import java.util.*;

public class Dijkstra {
    private PriorityQueue<Entry> queue;
    private HashMap<Node, Entry> tab;
    private List<Entry> visited;

    public Dijkstra(List<Node> nodes) {
        tab = new HashMap<>();
        queue = new PriorityQueue<>(new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2){
                if (o1.getVertex().isBase()) {
                    return -1;
                }
                else if (o2.getVertex().isBase()) {
                    return 1;
                }
//                boolean o1IsNeighbor = false;
//                boolean o2IsNeighbor = false;
//                for ( Entry entry : getVisited() ) {
//                    if (entry.getVertex().hasNeighbor(o1.getVertex())) {
//                        o1IsNeighbor = true;
//                    }
//                    if (entry.getVertex().hasNeighbor(o2.getVertex())) {
//                        o2IsNeighbor = true;
//                    }
//                }
//
//                if (o1IsNeighbor) {
//                    if (o2IsNeighbor) {
//                        return o2.getDistanceFromBase() - o1.getDistanceFromBase();
//                    }
//                    return -1;
//                }
//                if (o2IsNeighbor) {
//                    return 1;
//                }
//                return o2.getDistanceFromBase() - o1.getDistanceFromBase();
                if (o1.getPreviousNode() != null) {
                    if (o2.getPreviousNode() != null) {
                        return o1.getDistanceFromBase() - o2.getDistanceFromBase();
                    }
                    return -1;
                }
                if (o2.getPreviousNode() != null) {
                    return 1;
                }
                return o1.getDistanceFromBase() - o2.getDistanceFromBase();
            }});
        visited = new ArrayList<>();
        for (Node node : nodes) {
            Entry e = new Entry(node);
            tab.put(node, e);
            queue.add(e);
        }
    }

    public List<Entry> getVisited() {
        return visited;
    }

    public void run () {
        while (!queue.isEmpty()) {
//            System.out.println(queue);
            Entry current = queue.peek();
            Node node = current.getVertex();
            if (node.isBase()) {
                current.setDistanceFromBase(0);
                for (Edge edge : node.getEdges()) {
                    tab.get(edge.getSecond()).setPreviousNode(node);
                    tab.get(edge.getSecond()).setDistanceFromBase(edge.getDistance());
                }
            }
            else {

                for (Edge edge : node.getEdges()) {
//                    System.out.println("fail?");
                    if (edge.getDistance() + current.getDistanceFromBase() <= tab.get(edge.getSecond()).getDistanceFromBase()) {
                        tab.get(edge.getSecond()).setPreviousNode(node);
                        tab.get(edge.getSecond()).setDistanceFromBase(edge.getDistance() + current.getDistanceFromBase());
                    }
                }
            }
            queue.poll();
            visited.add(current);
        }
    }

    public void printTable() {
        System.out.println("Vertex  Distance  Previous Vertex");
        Collections.sort(visited);
        for (Entry entry : visited) {
            System.out.println(entry);
        }
    }

}
