package org.topcoder.dazdraperma;

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

  public class PermutationGenerator {

    private int[] a;
    private int numLeft;
    private int total;

    // -----------------------------------------------------------
    // Constructor. WARNING: Don't make n too large.
    // Recall that the number of permutations is n!
    // which can be very large, even when n is as small as 20 --
    // 20! = 2,432,902,008,176,640,000 and
    // 21! is too big to fit into a Java long, which is
    // why we use BigInteger instead.
    // ----------------------------------------------------------

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

    public int[] getNext() {

      if (numLeft == total) {
        numLeft--;
        return a;
      }

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
  }
  
  
  public int calc(String[] graph, String[] tree) {
    return 0;
  }

}
