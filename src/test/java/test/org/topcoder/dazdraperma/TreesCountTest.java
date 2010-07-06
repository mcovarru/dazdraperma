package test.org.topcoder.dazdraperma;

import org.topcoder.dazdraperma.TreesCount;

import junit.framework.TestCase;

public class TreesCountTest extends TestCase {
  
  private TreesCount counter;

  protected void setUp() throws Exception {
    super.setUp();
    counter = new TreesCount();
  }

  protected void tearDown() throws Exception {
    super.tearDown();
    counter = null;
  }
  
  
  public void test0() {
    assertEquals(1, counter.count(new String [] {"01", "10"}));
  }
  
  public void test1() {
    assertEquals(1, counter.count(new String [] {"011", "101", "110"}));
  }
  
  public void test2() {
    assertEquals(2, counter.count(new String [] {"021", "201", "110"}));
  }
  
  public void test3() {
    assertEquals(6, counter.count(new String [] {"0123", "1012", "2101", "3210"}));
  }
  
  public void test4() {
    assertEquals(2, counter.count(new String [] {"073542", "705141", "350721", "517031", "442304", "211140"}));
  }

}
