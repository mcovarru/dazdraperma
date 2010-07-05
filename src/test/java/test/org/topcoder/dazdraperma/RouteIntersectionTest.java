package test.org.topcoder.dazdraperma;

import org.topcoder.dazdraperma.RouteIntersection;

import junit.framework.TestCase;

public class RouteIntersectionTest extends TestCase {

  private RouteIntersection router;
  
  private static final String VALID = "VALID";
  
  private static final String NOT_VALID = "NOT VALID";

  protected void setUp() throws Exception {
    super.setUp();
    
    this.router = new RouteIntersection();
  }

  protected void tearDown() throws Exception {
    super.tearDown();
    this.router = null;
  }
  
  
  public void test0() {
    assertEquals(VALID, router.isValid(1, new int [] {1}, "+"));
  }

  
  public void test1() {
    assertEquals(NOT_VALID, router.isValid(2, new int [] {1,2,1,2}, "++--"));
  }
  
  public void test2() {
    assertEquals(VALID, router.isValid(3, new int [] {1,2,3,1,2}, "+++--"));
  }

  public void test3() {
    assertEquals(NOT_VALID,
        router.isValid(344447, new int [] {132,51717,628,344447,628,51717,344447,2}, "+-++-+--"));
  }
  
  
  public void test4() {
    assertEquals(NOT_VALID, router.isValid(1, new int [] {1,1}, "+-"));
  }
  
  
  public void test5() {
    assertEquals(NOT_VALID,
        router.isValid(990630, new int [] {833196,524568,361663,108056,28026,824639,269315,440977,440977,765458,
            988451,242440,948414,130873,773990,765458,130873,28026,853121,553636,
            581069,82254,735536,833196,898562,898562,940783,988451,540613,317306,
            623194,940783,571384,988451,108056,514374,97664}, "--+---+-+++-+-+---++-++-+---+-+--+-++"));    
  }
 
}
