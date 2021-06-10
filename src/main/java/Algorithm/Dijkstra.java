package Algorithm;
//
//import java.util.*;
//
//public class Dijkstra {
//    public static Object[][] dijkstras(WeightedGraph weightedGraph, Node source ) {
////        System.out.println("Starting: " + source.getIdentifier());
//
//        Object[][] chart = init(weightedGraph,source);
//        List<String> visited = new ArrayList<>();
//
//        String[] identifiers = new String[weightedGraph.getNodes().length];
//        for ( int i = 0; i < weightedGraph.getNodes().length; i++ ) {
//            identifiers[i] = weightedGraph.getNodes()[i].getIdentifier();
//        }
//        for ( String identifier : source.getEdges().keySet() ) {
//            chart[Arrays.binarySearch(identifiers, identifier)][1] = source.getEdges().get(identifier);
//            chart[Arrays.binarySearch(identifiers, identifier)][2] = source.getIdentifier();
//        }
//        visited.add(source.getIdentifier());
////        printChart(chart);
////        System.out.println(visited);
//
//        for ( String identifier : source.getConnectedNodeOrderedIdentifiers() ) {
//            chart = updateChart(weightedGraph, weightedGraph.get(identifier), source, visited, chart, identifiers);
//        }
//
//        for ( String identifier : source.getConnectedNodeOrderedIdentifiers() ) {
//            chart = dijkstras(weightedGraph, weightedGraph.get(identifier), visited, chart, identifiers);
//        }
////        printChart( chart );
//        return chart;
//    }
//
//    private static Object[][] updateChart(WeightedGraph weightedGraph, Node node, Node lastNode, List<String> visited, Object[][] chart, String[] identifiers ) {
////        System.out.println("Updating: " + node.getIdentifier());
//        for ( String identifier : node.getConnectedNodeOrderedIdentifiers() ) {
//            if ( !visited.contains(identifier) ) {
//                if ( !weightedGraph.get(identifier).equals(lastNode) && (Double)chart[Arrays.binarySearch(identifiers, identifier)][1] > node.getEdges().get(identifier) + (Double)chart[Arrays.binarySearch(identifiers, node.getIdentifier())][1] ) {
////                    System.out.println("Changing path to: " + identifier);
//                    chart[Arrays.binarySearch(identifiers, identifier)][1] = node.getEdges().get(identifier) + (Double)chart[Arrays.binarySearch(identifiers, node.getIdentifier())][1];
//                    chart[Arrays.binarySearch(identifiers, identifier)][2] = node.getIdentifier();
//                }
//            }
//        }
////        printChart( chart );
////        System.out.println(visited);
//
//        return chart;
//    }
//
//    private static Object[][] dijkstras(WeightedGraph weightedGraph, Node node, List<String> visited, Object[][] chart, String[] identifiers ) {
////        System.out.println("Recurring: " + node.getIdentifier());
//
//        for ( String identifier : node.getConnectedNodeOrderedIdentifiers() ) {
//            if ( !visited.contains(identifier) ) {
//                chart = updateChart(weightedGraph, weightedGraph.get(identifier), node, visited, chart, identifiers);
//            }
//        }
//        visited.add(node.getIdentifier());
//        for ( String identifier : node.getConnectedNodeOrderedIdentifiers() ) {
//            if ( !visited.contains(identifier) ) {
//                chart = dijkstras(weightedGraph, weightedGraph.get(identifier), visited, chart, identifiers);
//            }
//        }
//        return chart;
//
//    }
//
//    private static Object[][] init(WeightedGraph weightedGraph, Node source ) {
//        Object[][] chart = new Object[weightedGraph.size()][3];
//        Node[] nodes = weightedGraph.getNodes();
//
//        for ( int x = 0; x < chart.length; x++ ) {
//            for ( int y = 0; y < 2; y++ ) {
//                if ( y == 0 ) {
//                    chart[x][0] = nodes[x].getIdentifier();
//                }
//
//                if ( chart[x][0].equals(source.getIdentifier()) ) {
//                    chart[x][1] = Double.valueOf(0);
//                }
//                else if ( y == 1 ) {
//                    chart[x][1] = Double.valueOf(100000);
//                }
//            }
//        }
//        return chart;
//    }
//
//    public static void printChart( Object[][] chart ) {
//        System.out.println( String.format("%12s%11s%7s", "Node", "Distance", "Via") );
//        for ( Object[] x : chart ) {
//            for ( Object item : x ) {
//                System.out.print( String.format("%10s", item) );
//            }
//            System.out.println("");
//        }
//        System.out.println("");
//    }
//}

import java.util.*;

public class Dijkstra {
    private static PriorityQueue<Entry> queue;
    private static HashMap<Node, Entry> tab;
    public static List<Node> visited;

    public Dijkstra(List<Node> nodes) {
        tab = new HashMap<>();
        queue = new PriorityQueue<>();
        visited = new ArrayList<>();
        for (Node node : nodes) {
            Entry e = new Entry(node);
            tab.put(node, e);
            queue.add(e);
        }
    }

    public void run () {
        while (!queue.isEmpty()) {
            Entry current = queue.poll();
            Node node = current.getVertex();
            if (node.isBase()) {
                current.setDistanceFromBase(0);
                tab.put(node, current);
            }
            else {
                for (Edge edge : node.getEdges()) {
                    if (edge.getDistance() + tab.get(current.getPreviousNode()).getDistanceFromBase() <= tab.get(edge.getSecond()).getDistanceFromBase()) {
                        tab.get(edge.getSecond()).setDistanceFromBase(edge.getDistance() + tab.get(current.getPreviousNode()).getDistanceFromBase());
                        tab.get(edge.getSecond()).setPreviousNode(node);
                    }
                }
            }
            visited.add(node);
        }
    }

}
