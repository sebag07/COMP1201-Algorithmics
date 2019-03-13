import junit.framework.TestCase;

import java.util.NoSuchElementException;

/**
 * Author: Bogdan
 * Timestamp: 4/25/2016 10:55 PM
 */
public class TestGraph extends TestCase {

    public void testAddVertixMethod() {
        Graph<Integer> graph = new Graph<Integer>();
        for (int i = 0; i < 100; i++) {
            graph.addVertex(i, Math.random(), Math.random());
        }
        assertEquals("Vertices count should be 100", 100, graph.verticesCount());
    }
    public void testAddEdgeMethod() {
        Graph<Integer> graph = new Graph<Integer>();
        boolean exception = false;
        try{
            graph.addEdge(5,6,10);
        }catch (NoSuchElementException e){
            exception = true;
        }
        assertTrue("Exception should be throwed",exception);
        for (int i = 0; i < 100; i++) {
            graph.addVertex(i, Math.random(), Math.random());
        }
        graph.addEdge(5,6,10);
        graph.addEdge(5,6,10);
        graph.addEdge(6,5,10);
        graph.addEdge(10,6,10);
        assertEquals("Edges count should be 2", 2, graph.edgesCount());
    }

}
