package edu.union.adt.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.ArrayList;

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
 * @version 2.0
 *
 * Student: Logan Walker
 *
 * I affirm that I have carried out the attached academic endeavors with full academic honesty,
 * in accordance with the Union College Honor Code and the course syllabus.
 *
 */
public class LoganGraph<V> implements Graph<V>
{

    /**
     * contents: contains both the graph keys and the values for each key.
     * The keys are simply the inputted object value while the values
     * are contained within a HashSet. Each key must be different
     * and the values for a given key contains no duplicates, meaning
     * different keys may contain the same value but a single key cannot
     * possess two values that are identical.
     * numOfEdges: keeps track of the amount of edges within the contents
     * of the graph.
     */
    private HashMap<V, HashSet<V>> contents;
    private int numOfEdges;

    /**
     * Create an empty graph.
     */
    public LoganGraph()
    {
      contents = new HashMap<V, HashSet<V>>();
      numOfEdges = 0;
    }

    /**
     * @return the number of vertices in the graph.
     */
    public int numVertices()
    {
        return contents.size();
    }

    /**
     * @return the number of edges in the graph.
     */
    public int numEdges()
    {
      return numOfEdges;
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
        Set<V> vertices = contents.keySet();
        if (!vertices.contains(vertex)) {
          throw new RuntimeException("vertex not in graph");
        }

        HashSet<V> value = contents.get(vertex);
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
        HashSet<V> values = contents.get(from);
        values.add(to);
        numOfEdges++;
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
        if (!contents.containsKey(vertex)) {
              contents.put(vertex, new HashSet<V>());
        }
    }

    /**
     * @return the an iterable collection for the set of vertices of
     * the graph.
     */
    public Iterable<V> getVertices()
    {
        return contents.keySet();
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
        if (contents.containsKey(from)) {
            return contents.get(from);
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
        return contents.containsKey(vertex);
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
        if (numOfEdges == 0) {
            return false;
        }
        if (!this.contains(from)) {
            return false;
        }
        if (!this.contains(to)) {
            return false;
        }
        HashSet<V> values = contents.get(from);
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
            return contents.equals(otherLoganGraph.contents);
        }
    }

    /**
     * Tells whether the graph is empty.
     *
     * @return true iff the graph is empty. A graph is empty if it has
     * no vertices and no edges.
     */
    public boolean isEmpty() {
        if ((this.numVertices() == 0) && (this.numEdges() == 0)) {
          return true;
        }
        else {
          return false;
        }
    }


    /**
     * Removes a vertex from the graph.  Also removes any edges
     * connecting from the edge or to the edge.
     *
     * <p>Postconditions:
     *
     * <p>If toRemove was in the graph:
     * <ul>
     * <li>numVertices = numVertices' - 1
     * <li>toRemove is no longer a vertex in the graph
     * <li>for all vertices v: toRemove is not in adjacentTo(v)
     * </ul>
     *
     * @param toRemove the vertex to remove.
     */
    public void removeVertex(V toRemove) {
        if (this.contains(toRemove)) {
          Iterable<V> toRemoveAdjacencies = adjacentTo(toRemove);
          Iterator<V> toRemoveIterator = toRemoveAdjacencies.iterator();
          while(toRemoveIterator.hasNext()) {
            V destination = toRemoveIterator.next();
            removeEdge(toRemove, destination);
          }
          Iterable<V> verticesList = getVertices();
          Iterator<V> verticesIterator = verticesList.iterator();
          while(verticesIterator.hasNext()) {
            V currentVertex = verticesIterator.next();
            if(this.hasEdge(currentVertex, toRemove)) {
              removeEdge(currentVertex, toRemove);
            }
          }
          contents.remove(toRemove);
        }
    }


    /**
     * Removes an edge from the graph.
     *
     * <p>Postcondition: If from and to were in the graph and (from,
     * to) was an edge in the graph, then:
     * <ul>
     * <li> numEdges = numEdges' - 1
     * <li> to is no longer in adjacentTo(from)
     * </ul>
     *
     * @param from the source vertex for the edge
     * @param to the target vertex for the edge
     */
    public void removeEdge(V from, V to) {
      if (this.contains(from)) {
        if (this.contains(to)) {
          if (this.hasEdge(from, to)) {
            HashSet<V> values = contents.get(from);
            values.remove(to);
            numOfEdges--;
          }
        }
      }
    }


    /**
     * Tells whether there is a path connecting two given vertices.  A
     * path exists from vertex A to vertex B iff A and B are in the
     * graph and there exists a sequence x_1, x_2, ..., x_n where:
     *
     * <ul>
     * <li>x_1 = A
     * <li>x_n = B
     * <li>for all i from 1 to n-1, (x_i, x_{i+1}) is an edge in the graph.
     * </ul>
     *
     * It therefore follows that if vertex A is in the graph, there
     * is a path from A to A.
     *
     * @param from the source vertex
     * @param to the destination vertex
     * @return true iff there is a path from 'from' to 'to' in the graph.
     */
    public boolean hasPath(V from, V to) {
        Iterable<V> path = getPath(from, to);
        Iterator<V> thePath = path.iterator();
        if (thePath.hasNext()) {
          return true;
        }
        else {
          return false;
        }
    }


    /**
     * Gets the length of the shortest path connecting two given
     * vertices.  The length of a path is the number of edges in the
     * path.
     *
     * <ol>
     * <li>If from = to, the shortest path has length 0
     * <li>Otherwise, the shortest path length is the length of the shortest
     * possible path connecting from to to.
     * </ol>
     *
     * @param from the source vertex
     * @param to the destination vertex
     * @return the length of the shortest path from 'from' to 'to' in
     * the graph.  If there is no path, returns Integer.MAX_VALUE
     */
    public int pathLength(V from, V to) {
        Iterable<V> path = getPath(from, to);
        Iterator<V> thePath = path.iterator();
        int count = 0;
        while (thePath.hasNext()) {
          thePath.next();
          count++;
        }
        return count;
    }


    /**
     * Returns the vertices along the shortest path connecting two
     * given vertices.  The vertices are given in the order x_1,
     * x_2, x_3, ..., x_n, where:
     *
     * <ol>
     * <li>x_1 = from
     * <li>x_n = to
     * <li>for all i from 1 to n-1: (x_i, x_{i+1}) is an edge in the graph.
     * </ol>
     *
     * @param from the source vertex
     * @param to the destination vertex
     * @return an Iterable collection of vertices along the shortest
     * path from 'from' to 'to'.  The Iterable includes the source and
     * destination vertices. If there is no path from 'from' to 'to'
     * in the graph (e.g. if the vertices are not in the graph),
     * returns an empty Iterable collection of vertices.
     */
    public Iterable<V> getPath(V from, V to) {
        if (!this.contains(from)) {
          return new ArrayList<V>();
        }
        if (!this.contains(to)) {
          return new ArrayList<V>();
        }
        ArrayList<V> visited = new ArrayList<V>();
        ArrayList<ArrayList<V>> listOfLists = new ArrayList<ArrayList<V>>();
        listOfLists = getPath(from, to, listOfLists, visited);
        if (listOfLists.size() > 0) {
            ArrayList<V> shortestList = listOfLists.get(0);
            for(int i = 0; i < listOfLists.size(); i++) {
                if(listOfLists.get(i).size() < shortestList.size()) {
                    shortestList = listOfLists.get(i);
                }
            }
            return shortestList;
        }
        else {
            return new ArrayList<V>();
        }
    }

    private ArrayList<ArrayList<V>> getPath(V from, V to, ArrayList<ArrayList<V>> listOfLists, ArrayList<V> visited) {
        if(from == to) {
            //ArrayList<V> toAdd = new ArrayList<V>();

            visited.add(from);
            listOfLists.add(visited);
            return listOfLists;
        }
        if(from.equals(to)) {
            //ArrayList<V> toAdd = new ArrayList<V>();
            visited.add(from);
            listOfLists.add(visited);
            return listOfLists;
        }
        Iterable<V> adjacencys = adjacentTo(from);
        Iterator<V> neighbors = adjacencys.iterator();
        while (neighbors.hasNext()) {
            V neighbor = neighbors.next();
            if (!visited.contains(neighbor)){
                ArrayList<V> newlyVisited = (ArrayList<V>) visited.clone();
                newlyVisited.add(from);
                listOfLists = getPath(neighbor, to, listOfLists, newlyVisited);
            }
        }
        return listOfLists;
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
