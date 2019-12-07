import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Implement Graph by AdjList
 */
public class Graph {
    private ArrayList<String> actor_array;
    private ArrayList<LinkedList<String>> adj;
    public Graph() {
        actor_array = new ArrayList<String>();
        adj = new ArrayList<LinkedList<String>>();
    }
    public boolean inGraph(String actor) {
        return actor_array.contains(actor);
    }
    public void addEdges(String[] actors) {
        for(String actor: actors) {
            if(!inGraph(actor)) {
                actor_array.add(actor);
                LinkedList<String> temp = new LinkedList(Arrays.asList(actors));
                temp.remove(actor);
                adj.add(temp);
            }else {
                LinkedList<String> temp = adj.get(actor_array.indexOf(actor));
                for(String a: actors) {
                    if(!temp.contains(a))
                        temp.add(a);
                }
                temp.remove(actor);
                adj.set(actor_array.indexOf(actor),temp);
            }
        }
    }
    public ArrayList<String> findShortestRoute(String src, String dest) throws Exception {
        ArrayList<String> result = new ArrayList<String>();
        String[] visited = new String[actor_array.size()];
        LinkedList<String> queue = new LinkedList<String>();
        if(!inGraph(src) || !inGraph(dest)){
            throw new Exception();
        }
        visited[actor_array.indexOf(src)] = src;
        queue.add(src);
        while(queue.size() != 0) {
            String s = queue.poll();
            if(s.equals(dest)) {
                String curr = s;
                while(!curr.equals(src)) {
                    result.add(curr);
                    curr = visited[actor_array.indexOf(curr)];
                }
                result.add(curr);
                return result;
            }else {
                Iterator iterator = adj.get(actor_array.indexOf(s)).listIterator();
                while (iterator.hasNext()) {
                    String name = (String) iterator.next();
                    int i = actor_array.indexOf(name);
                    if (visited[i] == null) {
                        visited[i] = s;
                        queue.add(name);
                    }
                }
            }
        }
        return result;
    }
}
