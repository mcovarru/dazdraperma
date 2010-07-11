package test.org.topcoder.dazdraperma;

import org.topcoder.dazdraperma.GameWithGraphAndTree;

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
