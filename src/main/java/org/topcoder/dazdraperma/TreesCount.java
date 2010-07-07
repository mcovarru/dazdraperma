package org.topcoder.dazdraperma;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class TreesCount {
  
  
  public static class Path implements Iterable<Edge> {
    
    private List<Edge> edges = new ArrayList<Edge>();
    
    public Path(Path other) {
      this.edges = new ArrayList<Edge>(other.edges);
    }
    
    public Path() {
    }
    
    public int length() {
      int length = 0;
      // System.out.println("path has " + edges.size() + " edges");
      for (Edge edge : edges)
        length += edge.length;
      return length;
    }    

    public void add(Edge e) {
      this.edges.add(e);
    }
    
    public void reduce() {
      this.edges.remove(edges.size() - 1);
    }

    public Iterator<Edge> iterator() {
      return edges.iterator();
    }
    
    
    public boolean contains(Edge e) {
      return edges.contains(e);
    }
    
    
    public boolean containsNone(Set<Edge> otherEdges) {
      Set<Edge> tmp = new HashSet<Edge>(edges);
      tmp.removeAll(otherEdges);
      return edges.size() == tmp.size();
    }
    
  }
  
  public static class Vertex {
    
    
    public Vertex(int num) {
      this.num = num;
    }
    
    
    public int num;
    private Set<Edge> edges = new HashSet<Edge>();

    
    public boolean equals(Object other) {
      Vertex o = (Vertex) other;
      return num == o.num;
    }
    
    public int hashCode() {
      return num;
    }
    
    public int distanceTo(Vertex other) {
      Set<Path> pathsToVertex = pathsToVertex(other);
      if (pathsToVertex.isEmpty())
        return 0;
      
      return pathsToVertex.iterator().next().length();
    }
    
     
    private void pathsWorker(Set<Path> paths, Set<Vertex> pointsSeen, Path pathSoFar, Vertex target) {
      for (Edge edge : edges) {
        
        Vertex other = edge.other(this);
        
        if (other.equals(target)) {
          // found a path that ends on the target vertex
          Path newPath = new Path(pathSoFar);
          newPath.add(edge);
          paths.add(newPath);
          
        }
        else if (pointsSeen.contains(other)) {
          // cycle, ignore this edge
        }
        else {
          // potentially interesting
          pointsSeen.add(other);
          pathSoFar.add(edge);
          other.pathsWorker(paths, pointsSeen, pathSoFar, target);
          pointsSeen.remove(other);
          pathSoFar.reduce();
        }
      }
    }
    
    
    public Set<Path> pathsToVertex(Vertex target) {
      Set<Path> tmpPaths = new HashSet<Path>();
      Set<Vertex> pointsSeen = new HashSet<Vertex>();
      Path pathSoFar = new Path();
      pointsSeen.add(this);
      
      pathsWorker(tmpPaths, pointsSeen, pathSoFar, target);
      
      // prune suboptimal paths
      int minPath = 10;
      
      for (Path path : tmpPaths) {
        int distance = 0;
        for (Edge edge : path)
          distance += edge.length;
        if (distance < minPath)
          minPath = distance;
      }
      
      Set<Path> ret = new HashSet<Path>();
      
      for (Path path : tmpPaths) {
        int distance = 0;
        for (Edge edge : path)
          distance += edge.length;
        if (distance == minPath)
          ret.add(path);
      }
      
      
      return ret;
      
    }
    
    
  }

  
  public static class Edge {
    public Vertex a;
    public Vertex b;
    public int length;
    
    public Edge(Vertex a, Vertex b, Integer length) {
      this.a = a;
      this.b = b;
      this.length = length;
      a.edges.add(this);
      b.edges.add(this);
    }
    
    public boolean contains(Vertex v) {
      return a.equals(v) || b.equals(v);
    }
    
    public boolean equals(Object other) {
      Edge o = (Edge) other;
      return o.contains(a) && o.contains(b);
    }
    
    public int hashCode() {
      return a.hashCode() + b.hashCode();
    }
    
    
    public Vertex other(Vertex thiz) {
      if (a.equals(thiz)) return b;
      if (b.equals(thiz)) return a;
      throw new IllegalArgumentException("specified vertex is not on this edge!");
    }
  }
  
  
  public static class EdgeCombinator implements Iterable<Set<Edge>> {
    
    private Edge[] edges;
    
    private long MAX;
    
    private int current = 0;

    public EdgeCombinator(Set<Edge> edges) {
      this.edges = new Edge[edges.size()];
      edges.toArray(this.edges);
      this.MAX = Math.round(Math.pow(2, this.edges.length)) - 1;
    }

    @Override
    public Iterator<Set<Edge>> iterator() {
      
      return new Iterator<Set<Edge>>() {

        public boolean hasNext() {
          return current + 1 <= MAX;
        }


        public Set<Edge> next() {
          current++;
          Set<Edge> edgeSet = new HashSet<Edge>();
          
          for (int i = 0; i < edges.length; i++)
            if ((current & (1 << i)) != 0)
              edgeSet.add(edges[i]);
          

          return edgeSet;
        }

        public void remove() {
          throw new UnsupportedOperationException();
        }
        
      };
    }
    
  }
  
  
  

  public int count(String[] graph) {
    
    System.out.println("looking at problem with initial string " + graph[0]);
    
    Vertex [] vertices = new Vertex[graph.length];
    for (int i = 0; i < graph.length; i++)
      vertices[i] = new Vertex(i);
    
    Set<Edge> edgeSet = new HashSet<Edge>();
    
    for (int i = 0; i < graph.length; i++) {
      for (int j = i + 1; j < graph.length; j++) {
        Vertex a = vertices[i];
        Vertex b = vertices[j];
        
        // the Edge constructor adds the edge to the vertices
        // 0 means no edge
        int length = Integer.valueOf(graph[i].charAt(j)) - '0';
        if (length > 0) 
          edgeSet.add(new Edge(a, b, length));
      }
    }
    
    Vertex zeroVertex = vertices[0];
    List<Set<Path>> pathSets = new ArrayList<Set<Path>>();
    Set <Edge> essentialEdges = new HashSet<Edge>();
    
    // dummy zeroth element
    pathSets.add(null);
    for (int i = 1; i < graph.length; i++) {
      Set<Path> paths = zeroVertex.pathsToVertex(vertices[i]);
      pathSets.add(i, paths);
      if (paths.size() == 1)
        essentialEdges.addAll(paths.iterator().next().edges);
    }
    
    
    Set<Edge> nonEssentialEdges = new HashSet<Edge>(edgeSet);
    nonEssentialEdges.removeAll(essentialEdges);
    


    EdgeCombinator combinator = new EdgeCombinator(nonEssentialEdges);
    
    // look for a path from the zero vertex to another vertex that does 
    // not contain any of the edges in this combination.  if we can find
    // such a path for all vertexes, then this combination of deletions 
    // is workable and we can increase our count.
    
    int count = 0;
    
    for (Set<Edge> edgeCombo : combinator) {

      for (int i = 1; i < graph.length; i++) {
        Set<Path> paths = pathSets.get(i);
        for (Path path : paths) {
          if (path.containsNone(edgeCombo)) break;
        }
        break;
      }
      count++;
    }
    
    
    
    
    
    
    return count;
  }
}
