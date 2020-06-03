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

        //create new map
        Node aNode = new Node( "A" );
        Node bNode = new Node( "B" );
        Node cNode = new Node( "C" );
        Node dNode = new Node( "D" );
        Node eNode = new Node( "E" );
        Node fNode = new Node( "F" );
        Node gNode = new Node( "G" );
        Node hNode = new Node( "H" );

        HashMap<Double, String> aMap = new HashMap<>();
        aMap.put(8.0, "B");
        aMap.put(5.0, "D");
        aMap.put(2.0, "C");
        HashMap<Double, String> bMap = new HashMap<>();
        bMap.put(8.0, "A");
        bMap.put(2.0, "D");
        bMap.put(13.0, "F");
        HashMap<Double, String> cMap = new HashMap<>();
        cMap.put(2.0, "A");
        cMap.put(2.0, "D");
        cMap.put(5.0, "E");
        HashMap<Double, String> dMap = new HashMap<>();
        dMap.put(5.0, "A");
        dMap.put(2.0, "B");
        dMap.put(2.0, "C");
        dMap.put(1.0, "E");
        dMap.put(6.0, "F");
        HashMap<Double, String> eMap = new HashMap<>();
        eMap.put(1.0, "D");
        eMap.put(1.0, "G");
        eMap.put(5.0, "C");
        HashMap<Double, String> fMap = new HashMap<>();
        fMap.put(6.0, "D");
        fMap.put(2.0, "G");
        fMap.put(13.0, "B");
        fMap.put(3.0, "H");
        HashMap<Double, String> gMap = new HashMap<>();
        gMap.put(1.0, "E");
        gMap.put(3.0, "D");
        gMap.put(2.0, "F");
        gMap.put(6.0, "H");
        HashMap<Double, String> hMap = new HashMap<>();
        hMap.put(3.0, "F");
        hMap.put(6.0, "G");

        aNode.setConnections( aMap );
        bNode.setConnections( bMap );
        cNode.setConnections( cMap );
        dNode.setConnections( dMap );
        eNode.setConnections( eMap );
        fNode.setConnections( fMap );
        gNode.setConnections( gMap );
        hNode.setConnections( hMap );

        List<Node> nodes = new ArrayList<>();
        nodes.add( aNode );
        nodes.add( bNode );
        nodes.add( cNode );
        nodes.add( dNode );
        nodes.add( eNode );
        nodes.add( fNode );
        nodes.add( gNode );
        nodes.add( hNode );


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
