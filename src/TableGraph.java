import java.util.*;

/**
 * Implement Graph by HashTable
 */
public class TableGraph implements Graph{
    private Hashtable<String, LinkedList<String>> adj;

    /**
     * Constructor
     */
    public TableGraph() {
        adj = new Hashtable<String,LinkedList<String>>();
    }

    /**
     *
     * @param actor
     * @return
     */
    public boolean inGraph(String actor) {
        return adj.containsKey(actor);
    }

    /**
     *
     * @param actors
     */
    public void addEdges(String[] actors) {
        for(String actor: actors) {
            if(!inGraph(actor)) {
                LinkedList<String> temp = new LinkedList(Arrays.asList(actors));
                temp.remove(actor);
                adj.put(actor,temp);
            }else {
                LinkedList<String> temp = adj.get(actor);
                for(String a: actors) {
                    if(!temp.contains(a) || !actor.equals(a))
                        temp.add(a);
                }
                adj.replace(actor, temp);
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
        Hashtable<String, String> visited = new Hashtable<String, String>();
        LinkedList<String> queue = new LinkedList<String>();
        if(!inGraph(src) || !inGraph(dest)){
            throw new Exception();
        }
        visited.put(src, src);
        queue.add(src);
        while(queue.size() != 0) {
            String s = queue.poll();
            if(s.equals(dest)) {
                String curr = s;
                while(!curr.equals(src)) {
                    result.add(curr);
                    curr = visited.get(curr);
                }
                result.add(curr);
                return result;
            }else {
                for (String name : adj.get(s)) {
                    if (!visited.containsKey(name)) {
                        visited.put(name,s);
                        queue.add(name);
                    }
                }
            }
        }
        return result;
    }
}