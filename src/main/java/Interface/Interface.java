package Interface;

import Algorithm.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import javax.imageio.stream.FileImageOutputStream;
import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class Interface {

    private static Graph graph;

    static class Graph {
        private List<Node> nodes;

        public Graph() {
            nodes = new ArrayList<>();
        }

        public void addNodes(Node... nodes) {
            for (Node node : nodes) {
                this.nodes.add(node);
            }
        }

        public List<Node> getNodes() {
            return nodes;
        }

        public void createUML() {
            System.out.println("Creating file...");
            File mapsFile = newFile();
            System.out.println("Created file successfully. Path: " + mapsFile.getPath());

            System.out.println("Building UML diagram.....");
            try {
                FileWriter fileWriter = new FileWriter(mapsFile);
                fileWriter.append("@startuml\n");
                for (Node node : nodes) {
                    fileWriter.append("\t(" + node.getIdentifier() + ")\n");
                }
                for (Node node : nodes) {
                    for (Edge edge : node.getEdges()) {
                        fileWriter.append("\t" + edge.getFirst() + " --> " + edge.getSecond() + " : " + edge.getDistance() + "\n");
                    }
                    fileWriter.append("\n");
                }
                fileWriter.append("@enduml");
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("***To view uml diagram, you can use plant uml.....");
            System.out.println("done");
        }

        private static File newFile() {
            File file = null;
            boolean notDone = true;
            int i = 0;
            while ( notDone ) {
                if ( !Files.exists( new File( String.format( "src/Maps/map%d.puml", i )).toPath() )) {
                    file = new File( String.format( "src/Maps/map%d.puml", i ) );
                    file.getParentFile().mkdirs();
                    notDone = false;
                }
                i++;
            }
            return file;
        }
    }

    public static void main(String[] args ) {
        ///////////////create new map////////////////
        Node aNode = new Node( "A" );
        Node bNode = new Node( "B" );
        Node cNode = new Node( "C" );
        Node dNode = new Node( "D" );
        Node eNode = new Node( "E" );
        Node fNode = new Node( "F" );
        Node gNode = new Node( "G" );
        Node hNode = new Node( "H" );

        aNode.setAsBase();

        aNode.path(bNode, 8);
        aNode.path(cNode, 2);
        aNode.path(dNode, 5);
        bNode.path(aNode, 8);
        bNode.path(dNode, 2);
        bNode.path(fNode, 13);
        cNode.path(aNode, 2);
        cNode.path(dNode, 2);
        cNode.path(eNode, 5);
        dNode.path(bNode, 2);
        dNode.path(aNode, 5);
        dNode.path(cNode, 2);
        dNode.path(eNode, 1);
        dNode.path(gNode, 3);
        dNode.path(fNode, 6);
        eNode.path(cNode, 5);
        eNode.path(dNode, 1);
        eNode.path(gNode, 1);
        fNode.path(bNode, 13);
        fNode.path(dNode, 6);
        fNode.path(gNode, 2);
        fNode.path(hNode, 3);
        gNode.path(eNode, 1);
        gNode.path(dNode, 3);
        gNode.path(fNode, 2);
        gNode.path(hNode, 6);
        hNode.path(fNode, 3);
        hNode.path(gNode, 6);

        graph = new Graph();
        graph.addNodes(aNode, bNode, cNode, dNode, eNode ,fNode, gNode, hNode);
        ///////////////////////////////////////

        graph.createUML();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Apply Dijkstras?");
        if ( scanner.next().contains("y") ) {
            try {
                Dijkstra dijkstra = new Dijkstra(graph.getNodes());
                dijkstra.run();
                dijkstra.printTable();
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }
        System.out.println("Done");
    }
}
