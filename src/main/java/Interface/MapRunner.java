package Interface;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;

import java.nio.file.Files;

import java.util.Scanner;

import Algorithm.*;

public class MapRunner {
    public static void main ( String[] args ) {
        Scanner scanner = new Scanner( System.in );
        Gson gson = new Gson();

        System.out.println("Which map would you like to use? Please specify an integer");
        File mapFile = new File( String.format("src/Maps/map%d.json", scanner.nextInt()));
        if ( Files.exists(mapFile.toPath())) {
            System.out.println("File Found.");
//                try {
//                    System.out.println(Files.readString(mapFile.toPath()));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            System.out.println("Apply Dijkstras?");
            if ( scanner.next().contains("y") ) {
                try {
                    WeightedGraph graph = gson.fromJson( new FileReader(mapFile), WeightedGraph.class);
                    Dijkstra.printChart( Dijkstra.dijkstras( graph, graph.get("A") ) );
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
