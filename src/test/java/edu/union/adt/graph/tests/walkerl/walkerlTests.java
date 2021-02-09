package edu.union.adt.graph.tests.walkerl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import edu.union.adt.graph.Graph;
import edu.union.adt.graph.GraphFactory;
import java.util.Iterator;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

public class walkerlTests {

    private Graph<Integer> tester1;
    private Graph<String> tester2;
    private Graph<String> tester3;

    @Before
    public void setUp() {
        tester1 = GraphFactory.<Integer>createGraph();
        tester2 = GraphFactory.<String>createGraph();
        tester3 = GraphFactory.<String>createGraph();
    }

    @Test
    public void removeEdgeFromNoEdges() {
        Integer from = 78;
        Integer to = 999;
        tester1.removeEdge(from, to);
        assertEquals("removing an edge from an empty graph changes nothing",
                tester1.numEdges(), 0);
        tester1.addEdge(from, to);
        tester1.removeEdge(to, from);
        assertEquals("removing the backwards form of an edge does not remove the actual edge",
                tester1.numEdges(), 1);
        assertEquals("an unsuccessful edge removal shouldn't affect numVertices",
                tester1.numVertices(), 2);
    }

    @Test
    public void removeEdgeSuccessful() {
      Integer from = 78;
      Integer to = 999;
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
    public void emptyAndNonemptyGraphs() {
      assertTrue("a brand new graph should be empty", tester1.isEmpty());
      assertTrue("a brand new graph should be empty", tester2.isEmpty());
      Integer one = 78;
      tester1.addVertex(one);
      assertFalse("adding a vertex should make the graph not empty",
                tester1.isEmpty());
      tester1.removeVertex(one);
      assertTrue("removing all vertices of a graph makes it empty again",
                tester1.isEmpty());

      String greetings = "Hello!";
      String partings = "Goodbye!";
      tester2.addEdge(greetings, partings);
      assertFalse("adding an edge should make the graph not empty",
                tester2.isEmpty());
      tester2.removeEdge(greetings, partings);
      assertFalse("removing all edges without removing the vertices means"
                + "the graph is still not empty", tester2.isEmpty());
    }

    @Test
    public void removingBasicVertices() {
      Integer one = 123;
      Integer two = 654;
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

      String three = "Hello!";
      String four = "Goodbye!";
      tester2.addVertex(three);
      tester2.removeVertex(four);
      assertEquals("removing a non-existing vertex on a non-empty graph "
                + "should not affect numVertices", tester2.numVertices(), 1);
      assertFalse("removing a non-existing vertex on a non-empty graph "
                + "should not add the vertex to the graph", tester2.contains(four));
    }

    @Test
    public void removingVerticesFromEdges() {
      Integer one = 100;
      Integer two = 202;
      Integer three = 330;
      Integer four = 444;
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

      String five = "five";
      String six = "six";
      String seven = "seven";
      String eight = "eight";
      tester2.addEdge(five, six);
      tester2.addEdge(six, seven);
      tester2.removeVertex(six);
      assertEquals("removing a vertex that is both the source of an "
                + "edge and the destination of another edge should remove"
                + "remove the destination edge", tester2.numEdges(), 0);
      assertFalse("removing a vertex that is both the source of an "
                + "edge and the destination of another edge should remove"
                + "remove the destination edge", tester2.hasEdge(five, six));
      assertFalse("removing a vertex that is both the source of an "
                + "edge and the destination of another edge should remove"
                + "remove the source edge", tester2.hasEdge(six, seven));

      tester2.addEdge(eight, eight);
      tester2.removeVertex(eight);
      assertEquals("removing a vertex that is both source and the destination"
                + "of the SAME edge", tester2.numEdges(), 0);
      assertFalse("removing a vertex that is both source and the destination"
                + "of the SAME edge", tester2.hasEdge(eight, eight));

    }

    @Test
    public void pathsFromEdges() {
      Integer one = 1;
      Integer two = 22;
      Integer three = 333;
      tester1.addEdge(one, two);
      tester1.addEdge(two, three);
      assertTrue("a graph has a path if it is directly connected "
                + "by a single edge - Integer", tester1.hasPath(one, two));
      assertTrue("a graph has a path even if it is connected "
                + "through multiple edges - Integer", tester1.hasPath(one, three));
      assertFalse("a graph does not have the backwards path of an inserted path - Integer",
                tester1.hasPath(three, one));
      assertTrue("a path is naturally created within a single vertex - Integer",
                tester1.hasPath(two, two));

      String first = "Logan";
      String middle = "James";
      String last = "Walker";
      tester2.addEdge(first, middle);
      tester2.addEdge(middle, last);
      assertTrue("a graph has a path if it is directly connected "
                + "by a single edge - String", tester2.hasPath(first, middle));
      assertTrue("a graph has a path even if it is connected "
                + "through multiple edges - String", tester2.hasPath(first, last));
      assertFalse("a graph does not have the backwards path of an inserted path - String",
                tester2.hasPath(last, first));
      assertTrue("a path is naturally created within a single vertex - String",
                tester2.hasPath(middle, middle));
    }

    @Test
    public void findingLengthsOfExistingPaths() {
      String beforeFirst = "Mr.";
      String first = "Logan";
      String middle = "James";
      String last = "Walker";
      String afterLast = "the First";
      tester2.addEdge(beforeFirst, first);
      tester2.addEdge(first, middle);
      tester2.addEdge(middle, last);
      tester2.addEdge(last, afterLast);
      assertEquals("if a single path exists, gets the only path length possible",
                tester2.pathLength(beforeFirst, afterLast), 4);
      assertEquals("the source and destination vertex of the path are the same",
                tester2.pathLength(first, first), 0);

      tester2.addEdge(first, last);
      assertEquals("if a shortcut is added, the path length decreases respectively",
                tester2.pathLength(beforeFirst, afterLast), 3);
      assertEquals("if a path is just an edge, the path length is one",
                tester2.pathLength(beforeFirst, first), 1);
    }

    @Test
    public void findingLengthsOfImpossiblePaths() {
      String beforeFirst = "Mr.";
      String first = "Logan";
      String middle = "James";
      String last = "Walker";
      String afterLast = "the First";
      String absent = "Not in Graph";
      tester2.addEdge(beforeFirst, first);
      tester2.addEdge(first, middle);
      tester2.addEdge(middle, last);
      tester2.addEdge(last, afterLast);

      assertEquals("if a path does not exist, Integer.MAX_VALUE is returned",
                tester2.pathLength(afterLast, last), Integer.MAX_VALUE);
      assertEquals("if the source vertex is not in the graph, "
                + "Integer.MAX_VALUE is returned",
                tester2.pathLength(absent, last), Integer.MAX_VALUE);
      assertEquals("if the source vertex is not in the graph, "
                + "Integer.MAX_VALUE is returned",
                tester2.pathLength(first, absent), Integer.MAX_VALUE);
      assertEquals("if the source vertex and the destination vertex are the"
                + " same but the vertex is not in the graph, Integer.MAX_VALUE is returned",
                tester2.pathLength(absent, absent), Integer.MAX_VALUE);
    }

    @Test
    public void smallestPathOfPossible() {
      String beforeFirst = "Mr.";
      String first = "Logan";
      String middle = "James";
      String last = "Walker";
      String afterLast = "the First";
      tester2.addEdge(beforeFirst, first);
      tester2.addEdge(first, middle);
      tester2.addEdge(first, last);
      tester2.addEdge(first, afterLast);
      tester2.addEdge(middle, last);
      tester2.addEdge(last, afterLast);

      tester3.addEdge(beforeFirst, first);
      tester3.addEdge(first, afterLast);

      Iterable<String> pathFrom2 = tester2.getPath(beforeFirst, afterLast);
      Iterable<String> pathFrom3 = tester3.getPath(beforeFirst, afterLast);

      assertEquals("the shortest should be the path with a shortcut",
                pathFrom2, pathFrom3);
    }

    @Test
    public void gettingImpossiblePaths() {

      String first = "Logan";
      String last = "Walker";
      String absent = "Not in Graph";
      tester2.addEdge(first, last);

      Iterable<String> impossiblePath1 = tester2.getPath(absent, last);
      assertEquals("a path with a non-existing source vertex will"
      + "result in no path", impossiblePath1, GraphFactory.<String>createGraph().getPath(absent, absent));

      Iterable<String> impossiblePath2 = tester2.getPath(first, absent);
      assertEquals("a path with a non-existing destination vertex will"
      + "result in no path", impossiblePath2, GraphFactory.<String>createGraph().getPath(absent, absent));
    }

}
