package edu.union.adt.graph.tests.walkerl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import edu.union.adt.graph.Graph;
import edu.union.adt.graph.GraphFactory;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

public class walkerlTests {

    private Graph<String> tester1;
    private Graph<String> tester2;

    @Before
    public void setUp() {
        tester1 = GraphFactory.<String>createGraph();
        tester2 = GraphFactory.<String>createGraph();
    }

    @Test
    public void removeEdgeFromNoEdges() {
        String from = "Hello!";
        String to = "Goodbye!";
        tester1.removeEdge(from, to);
        assertEquals("removing an edge from an empty graph changes nothing",
                tester1.numEdges(), 0);
        tester1.addEdge(from, to);
        tester1.removeEdge(to,from);
        assertEquals("removing the backwards form of an edge does not remove the actual edge",
                tester1.numEdges(), 1);
    }

    @Test
    public void removeEdgeSuccessful() {
      String from = "Hello!";
      String to = "Goodbye!";
      tester1.addEdge(from, to);
      assertEquals("confirming the edge was successfully added",
                tester1.numEdges(), 1);
      tester1.removeEdge(from, to);
      assertEquals("removing the edge should reduce numEdges by 1",
                tester1.numEdges(), 0);
      assertEquals("removing the edge should NOT reduce the numVertices",
                tester1.numVertices(), 2);
      assertTrue("removing the edge should NOT remove the source vertex",
                tester1.contains(from));
      assertTrue("removing the edge should NOT remove the destination vertex",
                tester1.contains(to));
    }
}
