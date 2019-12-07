import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;

public class Tests {
    @Test
    public void Test_addEdge1(){
        Graph g = new ListGraph();
        g.addEdges(new String[]{"A", "B"});
        g.addEdges(new String[]{"C", "D"});
        g.addEdges(new String[]{"A", "B", "C"});
    }
    @Test
    public void Test_inGraph1(){
        Graph g = new ListGraph();
        g.addEdges(new String[]{"A", "B"});
        g.addEdges(new String[]{"C", "D"});
        g.addEdges(new String[]{"A", "B", "C"});
        Assert.assertEquals(true, g.inGraph("A"));
        Assert.assertEquals(true, g.inGraph("B"));
        Assert.assertEquals(true, g.inGraph("C"));
        Assert.assertEquals(true, g.inGraph("D"));
        Assert.assertEquals(false, g.inGraph("E"));

    }
    @Test
    public void Test_findShortestRoute1() throws Exception {
        Graph g = new ListGraph();
        g.addEdges(new String[]{"A", "B"});
        g.addEdges(new String[]{"C", "D"});
        g.addEdges(new String[]{"A", "B", "C"});
        Assert.assertEquals(Arrays.asList(new String[]{"D", "C", "A"}),g.findShortestRoute("A","D"));
        Assert.assertEquals(Arrays.asList(new String[]{"A", "B"}),g.findShortestRoute("B","A"));
        Assert.assertEquals(Arrays.asList(new String[]{"D", "C", "B"}),g.findShortestRoute("B","D"));
    }
    @Test
    public void Test_addEdge2(){
        Graph g = new TableGraph();
        g.addEdges(new String[]{"A", "B"});
        g.addEdges(new String[]{"C", "D"});
        g.addEdges(new String[]{"A", "B", "C"});
    }
    @Test
    public void Test_inGraph2(){
        Graph g = new TableGraph();
        g.addEdges(new String[]{"A", "B"});
        g.addEdges(new String[]{"C", "D"});
        g.addEdges(new String[]{"A", "B", "C"});
        Assert.assertEquals(true, g.inGraph("A"));
        Assert.assertEquals(true, g.inGraph("B"));
        Assert.assertEquals(true, g.inGraph("C"));
        Assert.assertEquals(true, g.inGraph("D"));
        Assert.assertEquals(false, g.inGraph("E"));

    }
    @Test
    public void Test_findShortestRoute2() throws Exception {
        Graph g = new TableGraph();
        g.addEdges(new String[]{"A", "B"});
        g.addEdges(new String[]{"C", "D"});
        g.addEdges(new String[]{"A", "B", "C"});
        Assert.assertEquals(Arrays.asList(new String[]{"D", "C", "A"}),g.findShortestRoute("A","D"));
        Assert.assertEquals(Arrays.asList(new String[]{"A", "B"}),g.findShortestRoute("B","A"));
        Assert.assertEquals(Arrays.asList(new String[]{"D", "C", "B"}),g.findShortestRoute("B","D"));
    }
    @Test
    public void Test_format_name() {
        CS245A2 t = new CS245A2();
        Assert.assertEquals("Wei Tian", t.formatName("wei tian"));
    }
}
