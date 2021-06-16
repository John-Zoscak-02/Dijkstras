package Exec;

import Algorithm.Dijkstra;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which map would you like to use?");
        int mapNum = scanner.nextInt();
        String path = "src/Map Makers/map" + mapNum + ".json";
        File file = new File(path);
        Gson gson = new Gson();
        if (file.exists() && file.canRead()) {
            try {
                JsonReader jsonReader = new JsonReader(new FileReader(file));
                Type REF_TYPE = new TypeToken<List<Ref>>() {
                }.getType();
                Graph graph = new Graph(gson.fromJson(jsonReader, REF_TYPE));
                graph.createUML();
                System.out.println("Applying Dijkstra's");
                try {
                    Dijkstra dijkstra = new Dijkstra(graph.getNodes());
                    dijkstra.run();
                    dijkstra.printTable();
                } catch ( Exception e ) {
                    e.printStackTrace();
                }
                System.out.println("Done");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
