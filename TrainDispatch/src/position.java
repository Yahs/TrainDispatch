public class position {
	
	private int x;
	private int y;
	private int max;
	
	// Sets position data using placement in array.
	// 
	public position(int x, int y) {
		x = this.x;
		y = this.y;
	}
      //x = startStation;
      //y = endStation;
      
    public int getDistance (int x, int y) {
      int dist = -1;
      if (isAdjacent()) {
        /* formula for distance */
        return dist;
      }
      return dist;
    }
      
    // Random assignment of adjacency
    public boolean isAdjacent() {
      double rand = Math.floor(5 * Math.random() % 4);
      if (rand % 2 == 0) {
    	  return true;
      }
      return false;
    }
    
}
