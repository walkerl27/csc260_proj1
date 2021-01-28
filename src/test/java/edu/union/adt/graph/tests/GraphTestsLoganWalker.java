package edu.union.adt.graph.tests;

import edu.union.adt.graph.Graph;
import edu.union.adt.graph.GraphFactory;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)

/**
 * Tests created by Logan Walker for Project 1, API 1
 *
 * Student: Logan Walker
 *
 * I affirm that I have carried out the attached academic endeavors with full academic honesty,
 * in accordance with the Union College Honor Code and the course syllabus.
 */
public class GraphTestsLoganWalker {

    private Graph<Integer> test;

    @Before
    public void setUp()
    {
        test = GraphFactory.createGraph();
    }

    @Test
    public void containsVertex() {
        Integer num = 420;
        assertFalse("an empty graph will return false", test.contains(num));

        test.addVertex(num);
        assertTrue("contains will return true should the desired " +
            "vertex exist in the graph", test.contains(num));
        assertTrue("contains will return true should the desired " +
            "vertex exist in the graph, even if it's in a wrapper class" +
            "such as int vs. Integer", test.contains(420));
    }

    @Test
    public void containsEdges() {
        Integer num1 = 420;
        Integer num2 = 421;

        test.addEdge(num1, num2);
        assertTrue("the source vertex will be contained in " +
                "the graph", test.contains(num1));
        assertTrue("the destination vertex will also be contained in " +
                "the graph", test.contains(num2));
    }

    @Test
    public void selfEdge() {
        Integer num1 = 420;

        test.addEdge(num1, num1);
        assertTrue("adding a self-looping edge adds the vertex " +
                "to the graph", test.contains(num1));
        assertTrue("self-looping edges are added to the graph",
                test.hasEdge(num1, num1));
        assertEquals("adding a new vertex with a self-looping " +
                "edge adds only one vertex and one edge", test.numEdges(), test.numVertices());
    }

    @Test
    public void addingIdenticalVertex() {
        Integer num = 420;
        test.addVertex(num);
        test.addEdge(num, num);
        assertEquals("adding an edge to an existing vertex should not create another vertex",
                test.numVertices(), 1);
        test.addEdge(420, 420);
        assertEquals("adding an edge to an existing vertex should not create another vertex" +
                "even if the element type is different", test.numVertices(), 1);
    }

    @Test
    public void stringForm() {
        assertEquals("two empty graphs are equal in String form",
                test.toString(), GraphFactory.createGraph().toString());

        Integer num = 420;
        test.addVertex(420);
        assertNotEquals("a graph with a vertex is different from an empty graph",
                test.toString(), GraphFactory.createGraph().toString());

        Graph<Integer> test2 = GraphFactory.createGraph();
        test2.addEdge(num, num);
        assertNotEquals("a graph with a vertex is different from a graph with an edge",
                test.toString(), test2.toString());
    }

    @Test
    public void adjacentToComplexities() {
        Integer num = 420;
        Integer dirtyNum = 69;
        System.out.println(test.adjacentTo(num));

        assertEquals("If vertex not in graph, it has no destinations",
                test.adjacentTo(num), GraphFactory.createGraph().adjacentTo(num));

        test.addVertex(num);
        assertEquals("adding a vertex does not give it a destination",
                test.adjacentTo(num), GraphFactory.createGraph().adjacentTo(num));

        test.addEdge(num, num);
        assertNotEquals("adding an edge gives the graph a destination that it is 'adjacent' to",
                test.adjacentTo(num), GraphFactory.createGraph().adjacentTo(num));

        test.addEdge(num, dirtyNum);
        assertEquals("being added as a destination does not give the vertex " +
                "any 'adjacent' destinations for itself", test.adjacentTo(dirtyNum),
                GraphFactory.createGraph().adjacentTo(dirtyNum));
        assertNotEquals("a vertex with adjacent destinations is different from a vertex without" +
                "'any adjacent destinations", test.adjacentTo(num), test.adjacentTo(dirtyNum));
    }
}
