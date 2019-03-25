import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Graph {
    private List<Vertex> vertexes;
    private List<Edge> edges;

    public Graph(List<Vertex> vertexes, List<Edge> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
    }

    public List<Vertex> getVertexes() {
        return vertexes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void addVertex(Vertex v) {
        vertexes.add(v);
    }

    public void addEdge(Edge e_original) {
        Edge e_reverse = new Edge(e_original.getId(), e_original.getDestination(), e_original.getSource(), e_original.getWeight());
        edges.add(e_original);
        edges.add(e_reverse);
    }

    public Edge findEdge(String source, String destination) {
        for (Edge e : this.edges) {
            if (e.getSource().getName().equals(source) && e.getDestination().getName().equals(destination)) 
                return e;
        }
        return null;
    }

    public Vertex findVertex(String name) {
        for (Vertex v : this.vertexes) {
            if (v.getName().equalsIgnoreCase(name)) {
                return v;
            }
        }
        return null;
    }

    public boolean hasVertex(String name) {
        for (Vertex v : this.vertexes) {
            if (v.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public void printVertexes() {
        for (Vertex vertex : this.vertexes) {
            System.out.println(vertex.getName() + " " + vertex.getId());
        }
    }
    public void printEdges() {
        for (Edge e : this.edges) {
            System.out.println(e.toString());
        }
    }

    public void printPath(LinkedList<Vertex> vpath) {
        String path = "";
        Vertex cur, prev;
        Edge e;
        int weight;
        int totalWeight = 0;
        Iterator<Vertex> iterator = vpath.iterator();

        prev = iterator.next();
        path += "[" + prev.getName();
        while (iterator.hasNext()) {
            path+= "==";
            cur = iterator.next();
            e = findEdge(prev.getName(), cur.getName());
            weight = e.getWeight();
            totalWeight += weight;
            path += weight + "==>" + cur.getName();
            prev = cur;
        }
        path += "]";
        
        System.out.println("\nThe shortest route is:");
        System.out.println(path);
        System.out.println("Total of " + totalWeight + " miles");
        System.out.println("...");
    }


}