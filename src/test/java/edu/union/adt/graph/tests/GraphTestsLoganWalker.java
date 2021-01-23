package edu.union.adt.graph.tests;

import edu.union.adt.graph.Graph;
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
        test = new Graph<Integer>();
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
}
