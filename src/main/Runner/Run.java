package Runner;

import Algorithm.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class Run {

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
            System.out.println("Creating UML file...");
            File mapsFile = newUMLFile();
            System.out.println("Created UML file successfully. Path: " + mapsFile.getPath());

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
        }

        private static File newUMLFile() {
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

        public void createJson() {
            System.out.println("Creating Json file...");
            System.out.println("didnt make file");
            File mapsFile = newJsonFile();
            System.out.println("Created Json file successfully. Path: " + mapsFile.getPath());

            System.out.println("Building Json diagram.....");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try {
                BufferedWriter fileWriter = new BufferedWriter( new FileWriter(mapsFile) );
                gson.toJson( this, fileWriter);
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Made Files");
        }

        private static File newJsonFile() {
            File file = null;
            boolean notDone = true;
            int i = 0;
            while ( notDone ) {
                if ( !Files.exists( new File( String.format( "src/Map Makers/map%d.json", i )).toPath() )) {
                    file = new File( String.format( "src/Map Makers/map%d.json", i ) );
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

        aNode.setAsBase();

        aNode.path(bNode, 6);
        aNode.path(dNode, 1);
        dNode.path(aNode, 1);
        dNode.path(bNode, 2);
        dNode.path(eNode, 1);
        bNode.path(aNode, 6);
        bNode.path(dNode, 2);
        bNode.path(eNode, 2);
        bNode.path(cNode, 5);
        eNode.path(dNode, 1);
        eNode.path(bNode, 2);
        eNode.path(cNode, 5);
        cNode.path(bNode, 5);
        cNode.path(eNode, 5);

        graph = new Graph();
        graph.addNodes(aNode, bNode, cNode, dNode, eNode);
        ///////////////////////////////////////

        graph.createUML();
        graph.createJson();

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
