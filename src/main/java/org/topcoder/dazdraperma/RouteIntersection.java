package org.topcoder.dazdraperma;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class RouteIntersection {
  
  
  public class Coordinates {
    
    private HashMap<Integer, Integer> coordMap;
    
    public Coordinates() {
      this.coordMap = new HashMap<Integer, Integer>();
    }
    
    
    public Coordinates(HashMap<Integer, Integer> coordMap) {
      this.coordMap = coordMap;
    }
    
    
    private Coordinates adjust(int incr, int coord) {
      
      @SuppressWarnings("unchecked")
      HashMap<Integer, Integer> newMap = (HashMap<Integer, Integer>) coordMap.clone();
      boolean preexists = newMap.containsKey(coord);
      
      // zeroes are modeled as non-entries.  very important for #equals()!
      if (preexists) {
        int oldval = newMap.get(coord);
        if (oldval + incr == 0)
          newMap.remove(coord);
        else
          newMap.put(coord, oldval + incr);
      }
      else
        newMap.put(coord, incr);
        
      return new Coordinates(newMap);
    }
    
    
    public Coordinates up(int coord) {
      return adjust(1, coord);
    }
    
    public Coordinates down(int coord) {
      return adjust(-1, coord);
    }
    
    
    public boolean equals(Object other) {
      Coordinates o = (Coordinates) other;
      return coordMap.equals(o.coordMap);
    }
    
    public int hashCode() {
      return coordMap.hashCode();
    }
  }
  

  public String isValid(int N, int [] coords, String moves) {
    boolean valid = true;
    
    Set<Coordinates> coordSet = new HashSet<RouteIntersection.Coordinates>();
    Coordinates lastCoord = new Coordinates();
    coordSet.add(lastCoord);
    
    for (int i = 0; i < coords.length; i++) {
      Coordinates newCoords;
      if (moves.charAt(i) == '+')
        newCoords = lastCoord.up(coords[i]);
      else
        newCoords = lastCoord.down(coords[i]);
      
      if (coordSet.contains(newCoords)) {
        valid = false;
        break;
      }
      coordSet.add(newCoords);
      lastCoord = newCoords;
    }
    
    return valid ? "VALID" : "NOT VALID"; 
  }

}
