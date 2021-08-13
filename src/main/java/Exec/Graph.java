package Exec;

import Algorithm.Edge;
import Algorithm.Node;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.*;

public class Graph {

    private List<Node> nodes;

    public Graph() {
        nodes = new ArrayList<>();
    }

    public Graph(List<Ref> refs) {
        HashMap<String, Node> map = new HashMap<>();
        for (Ref ref : refs) {
            map.put(ref.getIdentifier(), new Node(ref.getIdentifier(), ref.isBaseTag()));
        }
        for (Ref ref: refs) {
            List<Edge> edges = new ArrayList<>();
            for (int i = 0; i < ref.getNeighbors().size(); i++) {
                edges.add(new Edge(ref.getDistances().get(i), map.get(ref.getIdentifier()), map.get(ref.getNeighbors().get(i))));
            }
            map.get(ref.getIdentifier()).setEdges(edges);
        }
        nodes = new ArrayList<>(map.values());
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
            Set<Edge> edges = new TreeSet<>();
            for (Node node : nodes) {
                fileWriter.append("\t(" + node.getIdentifier() + ")\n");
                edges.addAll(node.getEdges());
            }
//            for (Node node : nodes) {
//                for (Edge edge : node.getEdges()) {
//                    fileWriter.append("\t" + edge.getFirst() + " --> " + edge.getSecond() + " : " + edge.getDistance() + "\n");
//                }
//                fileWriter.append("\n");
//            }
            for (Edge e : edges) {
                fileWriter.append("\t" + e.getFirst() + " -- " + e.getSecond() + " : " + e.getDistance() + "\n");
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

        List<Ref> refs = new ArrayList<>();
        for (Node node : nodes) {
            refs.add(new Ref(node));
        }

        try {
            BufferedWriter fileWriter = new BufferedWriter( new FileWriter(mapsFile) );
            gson.toJson( refs, fileWriter);
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