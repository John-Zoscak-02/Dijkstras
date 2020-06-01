import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class Main {

    private static final boolean MAKING = true;

    public static void main(String[] args ) {
        if (MAKING) {
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

            TreeMap<Double, String> aMap = new TreeMap<>();
                aMap.put(3.0, "B");
                aMap.put(5.0, "D");
                aMap.put(4.0, "C");
            TreeMap<Double, String> bMap = new TreeMap<>();
                bMap.put(3.0, "A");
                bMap.put(1.0, "D");
                bMap.put(4.0, "E");
            TreeMap<Double, String> cMap = new TreeMap<>();
                cMap.put(4.0, "A");
                cMap.put(3.0, "G");
            TreeMap<Double, String> dMap = new TreeMap<>();
                dMap.put(5.0, "A");
                dMap.put(1.0, "B");
                dMap.put(2.0, "E");
                dMap.put(4.0, "G");
            TreeMap<Double, String> eMap = new TreeMap<>();
                eMap.put(4.0, "B");
                eMap.put(2.0, "D");
                eMap.put(4.0, "F");
            TreeMap<Double, String> fMap = new TreeMap<>();
                fMap.put(4.0, "E");
                fMap.put(4.0, "G");
            TreeMap<Double, String> gMap = new TreeMap<>();
                gMap.put(4.0, "F");
                gMap.put(4.0, "D");
                gMap.put(3.0, "C");

            aNode.setConnections( aMap );
            bNode.setConnections( bMap );
            cNode.setConnections( cMap );
            dNode.setConnections( dMap );
            eNode.setConnections( eMap );
            fNode.setConnections( fMap );
            gNode.setConnections( gMap );

            List<Node> nodes = new ArrayList<>();
            nodes.add( aNode );
            nodes.add( bNode );
            nodes.add( cNode );
            nodes.add( dNode );
            nodes.add( eNode );
            nodes.add( fNode );
            nodes.add( gNode );

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
        else {
            Scanner scanner = new Scanner( System.in );
            Gson gson = new Gson();

            System.out.println("Which map would you like to use? Please specify an integer");
            File mapFile = new File( String.format("src/Maps/map%d.json", scanner.nextInt()));
            if ( Files.exists(mapFile.toPath())) {
                System.out.println("File Found. ");
                try {
                    System.out.println(Files.readString(mapFile.toPath()));
                } catch ( Exception e ) {
                    e.printStackTrace();
                }

                System.out.println("Apply Dijkstras?");
                if ( scanner.next().contains("y") ) {
                    try {
                        WeightedGraph graph = gson.fromJson( new FileReader(mapFile), WeightedGraph.class);
                        DijkstrasAlgorithm.dijkstras( graph, graph.get("A") );
                    } catch ( Exception e ) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Done");
            }
            else {
                System.out.println("Failed to find map");
            }
        }
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
