package edu.union.adt.graph;


/**
 *
 * Student: Logan Walker
 *
 * I affirm that I have carried out the attached academic endeavors with full academic honesty,
 * in accordance with the Union College Honor Code and the course syllabus.
 *
 */
public class GraphFactory {
    static public <V> Graph<V> createGraph() {
        return new LoganGraph<V>();
    }
}
