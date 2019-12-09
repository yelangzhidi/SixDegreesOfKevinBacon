import java.util.ArrayList;

public interface Graph {
    boolean inGraph(String actor);
    void addEdges(String[] actors);
    ArrayList<String> findShortestRoute(String src, String dest) throws Exception;
}
