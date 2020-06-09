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
        Node hNode = new Node( "H" );
        Node iNode = new Node( "I" );

        HashMap<String, Double> aMap = new HashMap<>();
        aMap.put("D", 7.0 );
        aMap.put("B", 10.0 );
        HashMap<String, Double> bMap = new HashMap<>();
        bMap.put("E", 15.0);
        bMap.put("A", 10.0);
        bMap.put("F", 12.0);
        bMap.put("C", 10.0);
        HashMap<String, Double> cMap = new HashMap<>();
        cMap.put("B", 10.0);
        cMap.put("D", 15.0);
        cMap.put("G", 8.0);
        cMap.put("H", 12.0);
        HashMap<String, Double> dMap = new HashMap<>();
        dMap.put("A", 7.0);
        dMap.put("C", 15.0);
        dMap.put("F", 7.0);
        HashMap<String, Double> eMap = new HashMap<>();
        eMap.put("B", 15.0);
        eMap.put("G", 13.0);
        eMap.put("H", 9.0);
        HashMap<String, Double> fMap = new HashMap<>();
        fMap.put("G", 15.0);
        fMap.put("H", 22.0);
        fMap.put("D", 7.0);
        fMap.put("B", 12.0);
        HashMap<String, Double> gMap = new HashMap<>();
        gMap.put("E", 13.0);
        gMap.put("F", 15.0);
        gMap.put("C", 8.0);
        gMap.put("I", 5.0);
        HashMap<String, Double> hMap = new HashMap<>();
        hMap.put("F", 22.0);
        hMap.put("E", 9.0);
        hMap.put("C", 12.0);
        hMap.put("I", 9.0);
        HashMap<String, Double> iMap = new HashMap<>();
        iMap.put("H", 9.0);
        iMap.put("G", 5.0);

        aNode.setEdges( aMap );
        bNode.setEdges( bMap );
        cNode.setEdges( cMap );
        dNode.setEdges( dMap );
        eNode.setEdges( eMap );
        fNode.setEdges( fMap );
        gNode.setEdges( gMap );
        hNode.setEdges( hMap );
        iNode.setEdges( iMap );

        List<Node> nodes = new ArrayList<>();
        nodes.add( aNode );
        nodes.add( bNode );
        nodes.add( cNode );
        nodes.add( dNode );
        nodes.add( eNode );
        nodes.add( fNode );
        nodes.add( gNode );
        nodes.add( hNode );
        nodes.add( iNode );

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
