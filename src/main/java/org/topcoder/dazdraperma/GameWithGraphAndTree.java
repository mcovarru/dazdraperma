package org.topcoder.dazdraperma;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class GameWithGraphAndTree {
  // --------------------------------------
  // Systematically generate permutations.
  // --------------------------------------
  
  public static int factorial(int n) {
    int ret = 1;
    
    for (int i = 1; i <= n; i++)
      ret *= n;
    
    return ret;
  }

  public class PermutationGenerator<E> implements Iterator<List<E>>, Iterable<List<E>> {

    private int[] a;
    private int numLeft;
    private int total;
    private E [] stuffToPermute;
    
    private List<E> buffer;

    // -----------------------------------------------------------
    // Constructor. WARNING: Don't make n too large.
    // Recall that the number of permutations is n!
    // which can be very large, even when n is as small as 20 --
    // 20! = 2,432,902,008,176,640,000 and
    // 21! is too big to fit into a Java long, which is
    // why we use BigInteger instead.
    // ----------------------------------------------------------

    public PermutationGenerator(int n, E [] stuff) {
      if (n < 0) {
        throw new IllegalArgumentException("Min 0");
      }
      stuffToPermute = stuff;
      buffer = new ArrayList<E>(stuff.length);
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
    
    private List<E> returnPermutation() {
      for (int i = 0; i < a.length; i++)
        buffer.set(i, stuffToPermute[a[i]]);
      
      return buffer;  
      
    }

    public List<E> next() {

      if (numLeft == total) {
        numLeft--;
        return returnPermutation();
      }
      
      if (numLeft < 0)
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
      return returnPermutation();

    }


    public Iterator<List<E>> iterator() {
      return this;
    }

    public boolean hasNext() {
      return numLeft >= 0;
    }


    public void remove() {
      throw new UnsupportedOperationException();
      
    }
  }
  
  
  public static class Graph {
    
    public int [] [] packing;
    
    public Graph(String [] graph) {
      packing = new int[graph.length][(graph.length + 1)/2];
      
      for (int g = 0; g < graph.length; g++) {
        for (int c = 0; c < graph.length; c++) {
          packing[g][c/2] *= 2;
          if (graph[g].charAt(c) == 'Y')
            packing[g][c/2]++;
        }
      }
    }
    
    /**
     * Can this other Pack be overlaid upon us?
     * @param other
     * @return
     */
    public boolean canOverlay(Graph other) {
     
      int negatory = ((int) Math.pow(2, packing.length)) - 1;
      
      for (int g = 0; g < packing.length; g++) {
        for (int c = 0; c < packing[g].length; c++) {
          if (((packing[g][c] ^ negatory) & other.packing[g][c]) != 0)
            return false;
        }
      }
      
      return true;
    }    
  }
  
  

  
  public int calc(String[] graph, String[] tree) {
    
    Graph g = new Graph(graph);
    
    Graph t = new Graph(tree);
    
    int count = 0;
    return count;
  }

}
