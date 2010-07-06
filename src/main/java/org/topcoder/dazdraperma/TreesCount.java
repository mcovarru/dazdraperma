package org.topcoder.dazdraperma;

import java.util.ArrayList;
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
