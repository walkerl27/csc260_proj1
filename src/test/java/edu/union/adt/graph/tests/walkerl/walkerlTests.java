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
        assertEquals("an unsuccessful edge removal shouldn't affect numVertices",
                tester1.numVertices(), 2);
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

    @Test
    public void isEmpty() {
      assertTrue("a brand new graph should be empty", tester1.isEmpty());
      assertTrue("a brand new graph should be empty", tester2.isEmpty());
      tester1.addVertex("Hello!");
      assertFalse("adding a vertex should make the graph not empty",
                tester1.isEmpty());
      tester2.addEdge("Hello!", "Goodbye");
      assertFalse("adding an edge should make the graph not empty",
                tester2.isEmpty());
    }

    @Test
    public void removeVertex() {
      String one = "one";
      String two = "two";
      String three = "three";
      String four = "four";
      String five = "five";
      tester1.addVertex(one);
      tester1.removeVertex(one);
      assertEquals("removing a vertex should reduce numVertices by one",
                tester1.numVertices(), 0);
      assertFalse("removing a vertex should remove the vertex",
                tester1.contains(one));
      tester1.addEdge(two, three);
      tester1.removeVertex(three);
      assertEquals("removing the destination vertex of an edge should reduce"
                + " numEdges by one", tester1.numEdges(), 0);
      assertFalse("removing the destination vertex of an edge should"
                + "remove the edge", tester1.hasEdge(two, three));
      tester1.addEdge(four, five);
      tester1.removeVertex(four);
      assertEquals("removing the source vertex of an edge should reduce"
                + " numEdges by one", tester1.numEdges(), 0);
      assertFalse("removing the source vertex of an edge should"
                + "remove the edge", tester1.hasEdge(four, five));
    }

    @Test
    public void hasPath() {
      String first = "Logan";
      String middle = "James";
      String last = "Walker";
      tester1.addEdge(first, middle);
      tester1.addEdge(middle, last);
      assertTrue("a graph has a path if it is directly connected "
                + "by a single edge", tester1.hasPath(first, middle));
      assertTrue("a graph has a path even if it is connected "
                + "through multiple edges", tester1.hasPath(first, last));
    }
}
