package org.topcoder.dazdraperma;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TreesCount {
  
  
  public class Vertex {
    
    public Vertex(int num) {
      this.num = num;
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
    
    private void pathsWorker(Set<List<Edge>> paths, Set<Vertex> pointsSeen, List<Edge> pathSoFar, Vertex target) {
      for (Edge edge : edges) {
        if (edge.other(this).equals(target)) {
          // found a path that ends on the target vertex
          List<Edge> goodPath = new ArrayList<Edge>();
          Collections.copy(pathSoFar, goodPath);
          goodPath.add(edge);
          paths.add(goodPath);
          
        }
        else if (pointsSeen.contains(edge.other(this))) {
          // cycle, ignore this edge
        }
        else {
          // potentially interesting
          pointsSeen.add(this);
          pathSoFar.add(edge);
          pathsWorker(paths, pointsSeen, pathSoFar, target);
          pointsSeen.remove(this);
          pathSoFar.remove(edge);
        }
      }
    }
    
    
    public Set<List<Edge>> pathsToVertex(Vertex target) {
      Set<List<Edge>> ret = new HashSet<List<Edge>>();
      Set<Vertex> pointsSeen = new HashSet<Vertex>();
      List<Edge> pathSoFar = new ArrayList<Edge>();
      pointsSeen.add(this);
      
      pathsWorker(ret, pointsSeen, pathSoFar, target);
      
      return ret;
      
    }
    
    
  }

  
  public class Edge {
    public Vertex a;
    public Vertex b;
    public int length;
    
    public Edge(Vertex a, Vertex b, Integer length) {
      this.a = a;
      this.b = b;
      this.length = length;
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
  
  
  
  

  public int count(String[] graph) {
    
    Vertex [] vertices = new Vertex[graph.length];
    for (int i = 0; i < graph.length; i++)
      vertices[i] = new Vertex(i);
    
    for (int i = 0; i < graph.length; i++) {
      for (int j = i; j < graph.length; j++) {
        Vertex a = vertices[i];
        Vertex b = vertices[j];
        Edge e = new Edge(a, b, Integer.valueOf(graph[i].charAt(j)));
        a.edges.add(e);
        b.edges.add(e);
      }
    }
    
    
    
    
    return 0;
  }
}
