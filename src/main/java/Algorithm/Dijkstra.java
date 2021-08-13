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

    public void run () {
        while (!queue.isEmpty()) {
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
        System.out.println(String.format("%-20s", "VERTEX") +
                String.format("%-20s", "DISTANCE") +
                String.format("%-20s", "PREVIOUS VERTEX"));
        Collections.sort(visited, new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                return o1.getDistanceFromBase() - o2.getDistanceFromBase();
            }
        });
        for (Entry entry : visited) {
            System.out.println(String.format("%-20s", entry.getVertex()) +
                    String.format("%-20s", entry.getDistanceFromBase()) +
                    String.format("%-20s", entry.getPreviousNode()) );
        }
    }

}
