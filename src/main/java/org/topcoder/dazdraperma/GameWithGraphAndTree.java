package org.topcoder.dazdraperma;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class GameWithGraphAndTree {
  // --------------------------------------
  // Systematically generate permutations.
  // --------------------------------------
  
  public static int factorial(int n) {
    int ret = 1;
    
    for (int i = 2; i <= n; i++)
      ret *= i;
    
    return ret;
  }

  public static class PermutationGenerator implements Iterable<int []>, Iterator<int []> {

    private int[] a;
    private int numLeft;
    private int total;
    
    public PermutationGenerator(int n) {
      if (n < 0) {
        throw new IllegalArgumentException("Min 0");
      }
      a = new int[n];
      total = factorial(n);
      reset();
    }

    // ------
    // Reset
    // ------

    public void reset() {
      for (int i = 0; i < a.length; i++) {
        a[i] = i;
      }
      numLeft = total;
    }

    // --------------------------------------------------------
    // Generate next permutation (algorithm from Rosen p. 284)
    // --------------------------------------------------------
    

    public int [] next() {

      if (numLeft == total) {
        numLeft--;
        return a;
      }
      
      if (numLeft <= 0)
        throw new NoSuchElementException();

      int temp;

      // Find largest index j with a[j] < a[j+1]

      int j = a.length - 2;
      while (a[j] > a[j + 1]) {
        j--;
      }

      // Find index k such that a[k] is smallest integer
      // greater than a[j] to the right of a[j]

      int k = a.length - 1;
      while (a[j] > a[k]) {
        k--;
      }

      // Interchange a[j] and a[k]

      temp = a[k];
      a[k] = a[j];
      a[j] = temp;

      // Put tail end of permutation after jth position in increasing order

      int r = a.length - 1;
      int s = j + 1;

      while (r > s) {
        temp = a[s];
        a[s] = a[r];
        a[r] = temp;
        r--;
        s++;
      }

      numLeft--;
      return a;

    }

    public boolean hasNext() {
      return numLeft > 0;
    }


    public void remove() {
      throw new UnsupportedOperationException();
      
    }

    public Iterator<int[]> iterator() {
      return this;
    }
  }
  
  
  public static class Graph {
    
    public int [] [] packing;
    
    public static int NEGATORY; 
    
    public Graph(String [] graph) {
      packing = new int[graph.length][(graph.length + 1)/2];
      NEGATORY = ((int) Math.pow(2, packing.length)) - 1;
      
      for (int g = 0; g < graph.length; g++) {
        for (int c = 0; c < graph.length; c++) {
          packing[g][c/2] *= 2;
          if (graph[g].charAt(c) == 'Y')
            packing[g][c/2]++;
        }
      }
    }
    
    /**
     * Can this other Graph be overlaid upon us?
     * @param other
     * @return
     */
    public boolean canOverlay(Graph other) {
     
      for (int g = 0; g < packing.length; g++) {
        for (int c = 0; c < packing[g].length; c++) {
          if (((packing[g][c] ^ NEGATORY) & other.packing[g][c]) != 0)
            return false;
        }
      }
      
      return true;
    }
    
    
    public int count(Graph other) {
      
      PermutationGenerator permuter = new PermutationGenerator(packing.length);
      int count = 0;
      
      int [] [] permutation = new int[packing.length][];
      int [] [] tmp;
      
      
      for (int [] a : permuter) {
        for (int i = 0; i < packing.length; i++)
          permutation[i] = packing[a[i]];
      
        tmp = packing;
        packing = permutation;
        if (this.canOverlay(other))
          count++;
        packing = tmp;
      }
      
      return count;
      
    }
    
  }
  
  

  
  public int calc(String[] graph, String[] tree) {
    
    Graph g = new Graph(graph);
    
    Graph t = new Graph(tree);

    return g.count(t);
  }

}
