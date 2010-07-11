package test.org.topcoder.dazdraperma;

import org.topcoder.dazdraperma.GameWithGraphAndTree;
import org.topcoder.dazdraperma.GameWithGraphAndTree.Graph;


import junit.framework.TestCase;

public class GameWithGraphAndTreeTest extends TestCase {
  
  private GameWithGraphAndTree game;

  protected void setUp() throws Exception {
    super.setUp();
    this.game = new GameWithGraphAndTree();    
  }

  protected void tearDown() throws Exception {
    super.tearDown();
    this.game = null;
  }
  
  
  public void testGraph() {
   
    Graph pack;
    
    pack = new Graph(new String [] {"NY", "YY"});
    assertEquals(1, pack.packing[0][0]);
    assertEquals(3, pack.packing[1][0]);
    
    
    pack = new Graph(new String [] {"NYNY", "YYYY", "NNNN", "NNYY"});
    
    assertEquals(1, pack.packing[0][0]);
    assertEquals(1, pack.packing[0][1]);
    assertEquals(3, pack.packing[1][0]);
    assertEquals(3, pack.packing[1][1]);
    assertEquals(0, pack.packing[2][0]);
    assertEquals(0, pack.packing[2][1]);
    assertEquals(0, pack.packing[3][0]);
    assertEquals(3, pack.packing[3][1]);
    
  }
  
  
  public void testGraphOverlay() {
    
    Graph g, t;
    
    g = new Graph(new String [] {"NY", "YY"});
    t = new Graph(new String [] {"NY", "YY"});    
    assertTrue(g.canOverlay(t));
    
    g = new Graph(new String [] {"YY", "YY"});
    assertTrue(g.canOverlay(t));
    
    assertFalse(t.canOverlay(g));
  }
  
  
  public void test0() {
    assertEquals(2, game.calc(
        new String [] {"NYN", "YNY", "NYN"},
        new String [] {"NYY", "YNN", "YNN"}));
  }
  
  public void test1() {
    assertEquals(12, game.calc(
        new String [] {"NYNNN", "YNYYY", "NYNYY", "NYYNY", "NYYYN"},
        new String [] {"NYNNN", "YNYNN", "NYNYN", "NNYNY", "NNNYN"}));
  }
  
  public void test2() {
    assertEquals(0, game.calc(
        new String [] {"NYNNNY", "YNYNNN", "NYNYNN", "NNYNYN", "NNNYNY", "YNNNYN"},
        new String [] {"NYNNYN", "YNNYNY", "NNNNYN", "NYNNNN", "YNYNNN", "NYNNNN"}));
  }
  
  public void test3() {
    assertEquals(2, game.calc(
        new String [] {"NYNNYN", "YNNYNY", "NNNNYN", "NYNNNN", "YNYNNN", "NYNNNN"},
        new String [] {"NNNYYN", "NNYNNN", "NYNNYY", "YNNNNN", "YNYNNN", "NNYNNN"}));
  }
  
  public void test4() {
    assertEquals(90, game.calc(
        new String [] {"NYNNNYNNY", "YNNNNNNYN", "NNNNYYNYY", "NNNNNYNNY", "NNYNNNYNY", "YNYYNNNYN", "NNNNYNNYN", "NYYNNYYNN", "YNYYYNNNN"},
        new String [] {"NNYNNNYYN", "NNNNYNNNN", "YNNNNNNNN", "NNNNNNYNN", "NYNNNNNYY", "NNNNNNNNY", "YNNYNNNNN", "YNNNYNNNN", "NNNNYYNNN"}));
  }  
}
