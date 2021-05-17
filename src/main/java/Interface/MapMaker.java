package Interface;

import Algorithm.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class MapMaker {

    public static void main(String[] args ) {
        System.out.println("Creating file...");
        File mapsFile = newFile();
        System.out.println("Created file successfully. Path: " + mapsFile.getPath());

        System.out.println("Building map according to code.....");

        ///////////////create new map////////////////
        Node aNode = new Node( "A" );
        Node bNode = new Node( "B" );
        Node cNode = new Node( "C" );
        Node dNode = new Node( "D" );
        Node eNode = new Node( "E" );
        Node fNode = new Node( "F" );
        Node gNode = new Node( "G" );

        HashMap<String, Double> aMap = new HashMap<>();
        aMap.put("B", 3.0);
        aMap.put("D", 5.0);
        aMap.put("C", 4.0);
        HashMap<String, Double> bMap = new HashMap<>();
        bMap.put("A", 3.0);
        bMap.put("D", 1.0);
        bMap.put("E", 4.0);
        HashMap<String, Double> cMap = new HashMap<>();
        cMap.put("A", 4.0);
        cMap.put("G", 3.0);
        HashMap<String, Double> dMap = new HashMap<>();
        dMap.put("A", 5.0);
        dMap.put("B", 1.0);
        dMap.put("E", 2.0);
        dMap.put("G", 4.0);
        HashMap<String, Double> eMap = new HashMap<>();
        eMap.put("B", 4.0);
        eMap.put("D", 2.0);
        eMap.put("F", 4.0);
        HashMap<String, Double> fMap = new HashMap<>();
        fMap.put("E", 4.0);
        fMap.put("G", 4.0);
        HashMap<String, Double> gMap = new HashMap<>();
        gMap.put("F", 4.0);
        gMap.put("D", 4.0);
        gMap.put("C", 3.0);

        aNode.setEdges( aMap );
        bNode.setEdges( bMap );
        cNode.setEdges( cMap );
        dNode.setEdges( dMap );
        eNode.setEdges( eMap );
        fNode.setEdges( fMap );
        gNode.setEdges( gMap );

        List<Node> nodes = new ArrayList<>();
        nodes.add( aNode );
        nodes.add( bNode );
        nodes.add( cNode );
        nodes.add( dNode );
        nodes.add( eNode );
        nodes.add( fNode );
        nodes.add( gNode );

        ///////////////////////////////////////

        WeightedGraph graph = new WeightedGraph(nodes);

        System.out.println("Created map");

        System.out.println("Serializing...");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            BufferedWriter fileWriter = new BufferedWriter( new FileWriter(mapsFile) );
            gson.toJson( graph, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Done");
        System.out.println(graph);

    }

    private static File newFile() {
        File file = null;
        boolean notDone = true;
        int i = 0;
        while ( notDone ) {
            if ( !Files.exists( new File( String.format( "src/Maps/map%d.json", i )).toPath() )) {
                file = new File( String.format( "src/Maps/map%d.json", i ) );
                file.getParentFile().mkdirs();
                notDone = false;
            }
            i++;
        }
        return file;
    }
}
