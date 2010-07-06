package test.org.topcoder.dazdraperma;

import java.util.Set;

import org.topcoder.dazdraperma.TreesCount;
import org.topcoder.dazdraperma.TreesCount.Edge;
import org.topcoder.dazdraperma.TreesCount.Path;
import org.topcoder.dazdraperma.TreesCount.Vertex;

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
  
  
  public void testPathsToVertex() {
    Vertex a = new Vertex(0);
    Vertex b = new Vertex(1);
    Edge e = new Edge(a, b, 1);

   
    Set<Path> paths = a.pathsToVertex(b);

    
    assertEquals(1, paths.size());
    Path path = paths.iterator().next();
    assertEquals(1, path.length());
    Edge eret = path.iterator().next();
    assertTrue(eret.equals(e));

    ////////////////////////////
    Vertex v0 = new Vertex(0);
    Vertex v1 = new Vertex(1);
    Vertex v2 = new Vertex(2);
    Vertex v3 = new Vertex(3);
    
    new Edge(v0, v1, 1);
    new Edge(v0, v2, 2);
    new Edge(v0, v3, 3);
    new Edge(v1, v2, 1);
    new Edge(v1, v3, 2);
    new Edge(v2, v3, 1);
    
    assertEquals(1, v0.pathsToVertex(v1).size());  
    assertEquals(2, v0.pathsToVertex(v2).size());   
    assertEquals(4, v0.pathsToVertex(v3).size());
    assertEquals(1, v1.pathsToVertex(v2).size());
    assertEquals(2, v1.pathsToVertex(v3).size());
    assertEquals(1, v2.pathsToVertex(v3).size());
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
