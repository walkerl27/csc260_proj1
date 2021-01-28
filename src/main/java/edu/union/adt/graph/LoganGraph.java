package edu.union.adt.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * A graph that establishes connections (edges) between objects of
 * (parameterized) type V (vertices).  The edges are directed.  An
 * undirected edge between u and v can be simulated by two edges: (u,
 * v) and (v, u).
 *
 * The API is based on one from
 *     http://introcs.cs.princeton.edu/java/home/
 *
 * Some method names have been changed, and the Graph type is
 * parameterized with a vertex type V instead of assuming String
 * vertices.
 *
 * @author Aaron G. Cass
 * @version 1.0
 *
 * Student: Logan Walker
 *
 * I affirm that I have carried out the attached academic endeavors with full academic honesty,
 * in accordance with the Union College Honor Code and the course syllabus.
 *
 */
public class LoganGraph<V>
{

    private HashMap<V, HashSet<V>> info;

    /**
     * Create an empty graph.
     */
    public LoganGraph()
    {
      info = new HashMap<V, HashSet<V>>();
    }

    /**
     * @return the number of vertices in the graph.
     */
    public int numVertices()
    {
        return info.size();
    }

    /**
     * @return the number of edges in the graph.
     */
    public int numEdges()
    {
      Set<V> keys = info.keySet();
      int count = 0;
      for(V vertex: keys){
          count += degree(vertex);
      }
      return count;
    }

    /**
     * Gets the number of vertices connected by edges from a given
     * vertex.  If the given vertex is not in the graph, throws a
     * RuntimeException.
     *
     * @param vertex the vertex whose degree we want.
     * @return the degree of vertex 'vertex'
     */
    public int degree(V vertex)
    {
        Set<V> vertices = info.keySet();
        if (!vertices.contains(vertex)) {
          throw new RuntimeException("vertex not in graph");
        }

        HashSet<V> value = info.get(vertex);
        return value.size();
    }

    /**
     * Adds a directed edge between two vertices.  If there is already an edge
     * between the given vertices, does nothing.  If either (or both)
     * of the given vertices does not exist, it is added to the
     * graph before the edge is created between them.
     *
     * @param from the source vertex for the added edge
     * @param to the destination vertex for the added edge
     */
    public void addEdge(V from, V to)
    {
        if (!this.contains(from)) {
              addVertex(from);
        }
        if (!this.contains(to)) {
              addVertex(to);
        }
        HashSet<V> values = info.get(from);
        values.add(to);
    }

    /**
     * Adds a vertex to the graph.  If the vertex already exists in
     * the graph, does nothing.  If the vertex does not exist, it is
     * added to the graph, with no edges connected to it.
     *
     * @param vertex the vertex to add
     */
    public void addVertex(V vertex)
    {
        if (!info.containsKey(vertex)) {
              info.put(vertex, new HashSet<V>());
        }
    }

    /**
     * @return the an iterable collection for the set of vertices of
     * the graph.
     */
    public Iterable<V> getVertices()
    {
        return info.keySet();
    }

    /**
     * Gets the vertices adjacent to a given vertex.  A vertex y is
     * "adjacent to" vertex x if there is an edge (x, y) in the graph.
     * Because edges are directed, if (x, y) is an edge but (y, x) is
     * not an edge, we would say that y is adjacent to x but that x is
     * NOT adjacent to y.
     *
     * @param from the source vertex
     * @return an iterable collection for the set of vertices that are
     * the destinations of edges for which 'from' is the source
     * vertex.  If 'from' is not a vertex in the graph, returns an
     * empty iterator.
     */
    public Iterable<V> adjacentTo(V from)
    {
        if (info.containsKey(from)) {
            return info.get(from);
        }
        else {
            return new HashSet<V>();
        }
    }

    /**
     * Tells whether or not a vertex is in the graph.
     *
     * @param vertex a vertex
     * @return true iff 'vertex' is a vertex in the graph.
     */
    public boolean contains(V vertex)
    {
        return info.containsKey(vertex);
    }

    /**
     * Tells whether an edge exists in the graph.
     *
     * @param from the source vertex
     * @param to the destination vertex
     *
     * @return true iff there is an edge from the source vertex to the
     * destination vertex in the graph.  If either of the given
     * vertices are not vertices in the graph, then there is no edge
     * between them.
     */
    public boolean hasEdge(V from, V to)
    {
        if (!this.contains(from)) {
            return false;
        }
        if (!this.contains(to)) {
            return false;
        }
        HashSet<V> values = info.get(from);
        return values.contains(to);
    }

    /**
     * Gives a string representation of the graph.  The representation
     * is a series of lines, one for each vertex in the graph.  On
     * each line, the vertex is shown followed by ": " and then
     * followed by a list of the vertices adjacent to that vertex.  In
     * this list of vertices, the vertices are separated by ", ".  For
     * example, for a graph with String vertices "A", "B", and "C", we
     * might have the following string representation:
     *
     * <PRE>
     * A: A, B
     * B:
     * C: A, B
     * </PRE>
     *
     * This representation would indicate that the following edges are
     * in the graph: (A, A), (A, B), (C, A), (C, B) and that B has no
     * adjacent vertices.
     *
     * Note: there are no extraneous spaces in the output.  So, if we
     * replace each space with '*', the above representation would be:
     *
     * <PRE>
     * A:*A,*B
     * B:
     * C:*A,*B
     * </PRE>
     *
     * @return the string representation of the graph
     */
    public String toString()
    {
        String toReturn = "";
        Iterable<V> vertices = getVertices();
        for(V vertex: vertices) {
            toReturn += vertex.toString() + ":";
            Iterable<V> values = adjacentTo(vertex);
            for (V value: values) {
                toReturn += " " + value.toString();
            }
            toReturn += "\n";
        }
        return toReturn;
    }

    /**
     * Confirms whether or not two graphs share exactly
     * the same vertices and corresponding edges.
     *
     * @param other the object to compare with
     * @return true, if the graphs are equal. If not, false.
     */
    public boolean equals(Object other)
    {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LoganGraph)) {
            return false;
        }
        else {
            LoganGraph<V> otherLoganGraph = (LoganGraph<V>) other;
            return info.equals(otherLoganGraph.info);
        }
    }

    // Logan's messy testing for LoganGraph.java :P
    public static void main(String[] args) {
      HashMap<String, HashSet<String>> tester = new HashMap<String, HashSet<String>>();
      HashSet<String> temp = new HashSet<String>();
      temp.add("a");
      tester.put("A", temp);
      System.out.println(tester);
      System.out.println(tester.values());
      HashSet<String> temp2 = new HashSet<String>();
        temp2.add("b");
        tester.put("A", temp2);
        System.out.println(tester.values());
        System.out.println(tester.values().size());
    }
}
