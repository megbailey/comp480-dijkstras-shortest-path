import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class ShortestPath {
    public static void main(String[] args) {
    	
    	String filename = "";
    	for(int i = 2; i < args[0].length(); i++) {
    		filename += args[0].charAt(i);
    	}

        String startingCity, destinationCity;
        Vertex startingVertex = null, destinationVertex = null;

        Scanner input = new Scanner(System.in);
        Graph graph = initGraph(filename);
        Dijkstra dijkstra = new Dijkstra(graph);

        String cont = "y";
        System.out.println("Bailey’s Shortest Road Finder:");

        while (!cont.equalsIgnoreCase("Q")) {
           
            System.out.print("\nEnter the starting city: ");
            startingCity = input.next();
            System.out.print("Enter the destination city: ");
            destinationCity = input.next();

            startingVertex = graph.findVertex(startingCity);
            destinationVertex = graph.findVertex(destinationCity);

            dijkstra.execute(startingVertex);
            LinkedList<Vertex> path = dijkstra.getPath(destinationVertex);
            if (path != null) {
                graph.printPath(path);
            } else {
                System.out.println("No Route was found");
            }

            System.out.print("\nPlease enter “Q/q” to quit, any other key to continue: ");
            cont = input.next();

        }
        input.close();

    }

    public static Graph initGraph(String filename) {
        List<Vertex> nodes = new ArrayList<Vertex>();
        List<Edge> edges = new ArrayList<Edge>();

        Graph graph = new Graph(nodes, edges);

        try {
            String line;
            String[] line_ar = null;
            int vertex_id = 0, edge_id = 0;
            Vertex source, destination;
            BufferedReader br = new BufferedReader(new FileReader(new File(filename))); 

            while ((line = br.readLine()) != null) {

                line_ar = line.split(" ");
                
                if (graph.hasVertex(line_ar[0])) {
                    source = graph.findVertex(line_ar[0]);
                } else {
                    source = new Vertex(vertex_id + "", line_ar[0]);
                    vertex_id++;
                    graph.addVertex(source);
                }

                if (graph.hasVertex(line_ar[1])) {
                    destination = graph.findVertex(line_ar[1]);
                } else {
                    destination = new Vertex(vertex_id + "", line_ar[1]);
                    vertex_id++;
                    graph.addVertex(destination);
                }

                int weight = Integer.parseInt(line_ar[2]);
                Edge edge = new Edge(edge_id + "", source, destination, weight);
                graph.addEdge(edge);
                edge_id++;
            }
            br.close();

          } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        return graph;

    }


}
