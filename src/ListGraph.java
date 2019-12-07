import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Implement Graph by AdjList
 */
public class ListGraph implements Graph {
    private ArrayList<String> actor_array;
    private ArrayList<LinkedList<String>> adj;

    /**
     * Constructor
     */
    public ListGraph() {
        actor_array = new ArrayList<String>();
        adj = new ArrayList<LinkedList<String>>();
    }

    /**
     *
     * @param actor
     * @return
     */
    public boolean inGraph(String actor) {
        return actor_array.contains(actor);
    }

    /**
     *
     * @param actors
     */
    public void addEdges(String[] actors) {
        for(String actor: actors) {
            if(!inGraph(actor)) {
                actor_array.add(actor);
                LinkedList<String> temp = new LinkedList(Arrays.asList(actors));
                temp.remove(actor);
                adj.add(temp);
            }else {
                int index = actor_array.indexOf(actor);
                LinkedList<String> temp = adj.get(index);
                for(String a: actors) {
                    if(!temp.contains(a))
                        temp.add(a);
                }
                temp.remove(actor);
                adj.set(index,temp);
            }
        }
    }

    /**
     *
     * @param src
     * @param dest
     * @return
     * @throws Exception
     */
    public ArrayList<String> findShortestRoute(String src, String dest) throws Exception {
        ArrayList<String> result = new ArrayList<String>();
        String[] visited = new String[actor_array.size()];
        LinkedList<String> queue = new LinkedList<String>();
        int count = 0;
        if(!inGraph(src) || !inGraph(dest)){
            throw new Exception();
        }
        visited[actor_array.indexOf(src)] = src;
        queue.add(src);
        while(queue.size() != 0) {
            if(count == actor_array.size())
                return null;
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
                int index = actor_array.indexOf(s);
                for (String name : adj.get(index)) {
                    int i = actor_array.indexOf(name);
                    if (visited[i] == null) {
                        visited[i] = s;
                        count++;
                        queue.add(name);
                    }
                }
            }
        }
        return result;
    }
}
