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

    public int size() {
      return edges.size();
    }
    
    public void add(Edge e) {
      this.edges.add(e);
    }
    
    public void reduce() {
      this.edges.remove(edges.size() - 1);
    }

    
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{ ");

      Vertex last = null;

      if (edges.size() > 1) {
        Vertex common = Vertex.inCommon(edges.get(0), edges.get(1));
        Vertex first = edges.get(0).other(common);
        sb.append("[ " + first.num + " -> " + common.num + " + ] --> ");
        last = edges.get(1).other(common);
        sb.append("[ " + common.num + " -> " + last.num + "] ");
      }
      
      for (int i = 2; i < edges.size(); i++) {
        
        sb.append("[ " + last.num + " --> ");
        last = edges.get(i).other(last);
        sb.append(last.num + " ] ");
      }
      
      sb.append (" }");
        
      
      return sb.toString();
    }

    @Override
    public Iterator<Edge> iterator() {
      return edges.iterator();
    }
    
  }
  
  public static class Vertex {
    
    
    public Vertex(int num) {
      this.num = num;
    }
    
    
    public static Vertex inCommon(Edge e1, Edge e2) {
      if (e1.a.equals(e2.a) || e1.a.equals(e2.b)) return e1.a;
      return e1.b;
    }
    
    public int num;
    public Set<Edge> edges = new HashSet<Edge>();
    
    public boolean equals(Object other) {
      Vertex o = (Vertex) other;
      return num == o.num;
    }
    
    public int hashCode() {
      return num;
    }
    
    public String toString() {
      return "[Vertex " + num + "]";
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
          // System.out.println(prefix + "we have already seen vertex " + other + ", ignoring");
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
    
    
    public String toString() {
      return "[" + a.num + " --> " + b.num + "]";
    }
    
    public Vertex other(Vertex thiz) {
      if (a.equals(thiz)) return b;
      if (b.equals(thiz)) return a;
      throw new IllegalArgumentException("specified vertex is not on this edge!");
    }
  }
  
  
  
  

  public int count(String[] graph) {
    
    Vertex [] vertices = new Vertex[graph.length];
    for (int i = 0; i < graph.length; i++)
      vertices[i] = new Vertex(i);
    
    for (int i = 0; i < graph.length; i++) {
      for (int j = i; j < graph.length; j++) {
        Vertex a = vertices[i];
        Vertex b = vertices[j];
        
        // the Edge constructor adds the edge to the vertices
        new Edge(a, b, Integer.valueOf(graph[i].charAt(j)));
      }
    }
    
    
    
    
    return 0;
  }
}
