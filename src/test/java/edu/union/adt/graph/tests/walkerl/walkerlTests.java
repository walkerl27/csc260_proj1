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
    public void removingBasicVertices() {
      String one = "one";
      String two = "two";
      tester1.addVertex(one);
      tester1.removeVertex(one);
      assertEquals("removing a vertex should reduce numVertices by one",
                tester1.numVertices(), 0);
      assertFalse("removing a vertex should successfully remove the vertex",
                tester1.contains(one));

      tester1.removeVertex(two);
      assertEquals("removing a non-existing vertex on an empty graph "
                + "should not affect numVertices", tester1.numVertices(), 0);
      assertFalse("removing a non-existing vertex on an empty graph "
                + "should not add the vertex to the graph", tester1.contains(two));

      tester2.addVertex(one);
      tester2.removeVertex(two);
      assertEquals("removing a non-existing vertex on a non-empty graph "
                + "should not affect numVertices", tester2.numVertices(), 1);
      assertEquals("removing a non-existing vertex on a non-empty graph "
                + "should not add the vertex to the graph", tester2.contains(two));
    }

    @Test
    public void removingVerticesFromEdges() {
      String one = "one";
      String two = "two";
      String three = "three";
      String four = "four";
      tester1.addEdge(one, two);
      tester1.removeVertex(two);
      assertEquals("removing the destination vertex of an edge should reduce"
                + " numEdges by one", tester1.numEdges(), 0);
      assertFalse("removing the destination vertex of an edge should "
                + "remove the edge", tester1.hasEdge(one, two));

      tester1.addEdge(three, four);
      tester1.removeVertex(three);
      assertEquals("removing the source vertex of an edge should reduce"
                + " numEdges by one", tester1.numEdges(), 0);
      assertFalse("removing the source vertex of an edge should"
                + "remove the edge", tester1.hasEdge(three, four));

      tester2.addEdge(one, two);
      tester2.addEdge(two, three);
      tester2.removeVertex(two);
      assertEquals("removing a vertex that is both the source of an "
                + "edge and the destination of another edge should remove"
                + "remove the destination edge", tester2.numEdges(), 0);
      assertFalse("removing a vertex that is both the source of an "
                + "edge and the destination of another edge should remove"
                + "remove the destination edge", tester2.hasEdge(one, two));
      assertFalse("removing a vertex that is both the source of an "
                + "edge and the destination of another edge should remove"
                + "remove the source edge", tester2.hasEdge(two, three));

      tester2.addEdge(four, four);
      tester2.removeVertex(four);
      assertEquals("removing a vertex that is both source and the destination"
                + "of the SAME edge", tester2.numEdges(), 0);
      assertFalse("removing a vertex that is both source and the destination"
                + "of the SAME edge", tester2.hasEdge(four, four));

    }

    @Test
    public void pathsFromEdges() {
      String first = "Logan";
      String middle = "James";
      String last = "Walker";
      tester1.addEdge(first, middle);
      tester1.addEdge(middle, last);
      assertTrue("a graph has a path if it is directly connected "
                + "by a single edge", tester1.hasPath(first, middle));
      assertTrue("a graph has a path even if it is connected "
                + "through multiple edges", tester1.hasPath(first, last));
      assertFalse("a graph does not have the backwards path of an inserted path",
                tester1.hasPath(last, first));
      assertTrue("a path is naturally created within a single vertex",
                tester1.hasPath(middle, middle));
    }

    @Test
    public void findingLengthsOfExistingPaths() {
      String beforeFirst = "Mr.";
      String first = "Logan";
      String middle = "James";
      String last = "Walker";
      String afterLast = "the First";
      tester1.addEdge(beforeFirst, first);
      tester1.addEdge(first, middle);
      tester1.addEdge(middle, last);
      tester1.addEdge(last, afterLast);
      assertEquals("if a single path exists, gets the only path length possible",
                tester1.pathLength(beforeFirst, afterLast), 4);
      assertEquals("the source and destination vertex of the path are the same",
                tester1.pathLength(first, first), 0);
      tester1.addEdge(first, last);
      assertEquals("if a shortcut is added, the path length decreases respectively",
                tester1.pathLength(beforeFirst, afterLast), 3);
      assertEquals("if a path is just an edge, the path length is one",
                tester1.pathLength(beforeFirst, first), 1);
    }

    @Test
    public void findingLengthsOfImpossiblePaths() {
      String beforeFirst = "Mr.";
      String first = "Logan";
      String middle = "James";
      String last = "Walker";
      String afterLast = "the First";
      String absent = "Not in Graph";
      tester1.addEdge(beforeFirst, first);
      tester1.addEdge(first, middle);
      tester1.addEdge(middle, last);
      tester1.addEdge(last, afterLast);
      assertEquals("if a path does not exist, Integer.MAX_VALUE is returned",
                tester1.pathLength(afterLast, last), Integer.MAX_VALUE);
      assertEquals("if the source vertex is not in the graph, "
                + "Integer.MAX_VALUE is returned",
                tester1.pathLength(absent, last), Integer.MAX_VALUE);
      assertEquals("if the source vertex is not in the graph, "
                + "Integer.MAX_VALUE is returned",
                tester1.pathLength(first, absent), Integer.MAX_VALUE);
      assertEquals("if the source vertex and the destination vertex are the"
                + " same but the vertex is not in the graph, Integer.MAX_VALUE is returned",
                tester1.pathLength(absent, absent), Integer.MAX_VALUE);
    }
}
