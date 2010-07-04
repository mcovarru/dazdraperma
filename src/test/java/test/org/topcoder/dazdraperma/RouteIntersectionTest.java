package test.org.topcoder.dazdraperma;

import org.topcoder.dazdraperma.RouteIntersection;

import junit.framework.TestCase;

public class RouteIntersectionTest extends TestCase {

  private RouteIntersection router;

  protected void setUp() throws Exception {
    super.setUp();
    
    this.router = new RouteIntersection();
  }

  protected void tearDown() throws Exception {
    super.tearDown();
    this.router = null;
  }

}
