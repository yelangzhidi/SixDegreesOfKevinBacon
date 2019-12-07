import java.util.ArrayList;

public interface Graph {
    public boolean inGraph(String actor);
    public void addEdges(String[] actors);
    public ArrayList<String> findShortestRoute(String src, String dest) throws Exception;
}
