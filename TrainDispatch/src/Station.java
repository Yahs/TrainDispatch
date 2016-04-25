import java.util.ArrayList;
import java.util.List;


public class Station<Key extends Comparable<Key>> {
  
  private int stationNum;
  private int posX;
  private int posY;
  private List<Edge> edges = new ArrayList<Edge>();
  private boolean visited = false;
  
  public Station() {
	  
  }
  
  public Station (int num) {
	  this.stationNum = num;
  }
  
  public Station(int num, int x, int y) {
	this.stationNum = num;
	this.posX = x;
	this.posY = y;
  }
  
  public void setNum(int num) {
	this.stationNum = num;
  }
  
  public void setPos(int x, int y) {
	this.posX = x;
	this.posY = y;
  }
  
  public int getNum() {
	return this.stationNum;
  }
  
  public int getX() {
	return this.posX;
  }
  
  public int getY() {
	return this.posY;
  }
  
  public double getAngle(Station end) {
	int x1, x2, y1, y2, diffX, diffY;
	double angle;
	x1 = this.getX();
	x2 = end.getX();
	y1 = this.getY();
	y2 = end.getY();
	diffX = x2 - x1;
	diffY = y2 - y1;
	angle = Math.toDegrees(Math.atan2(diffY, diffX)) % 360;
	return angle;
  }
  
  public Edge getEdge(Station end, int num) {
	int x1, x2, y1, y2, dist;
	x1 = this.getX();
	x2 = end.getX();
	y1 = this.getY();
	y2 = end.getY();
	dist = (int) Math.sqrt((Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2)));
	
	return new Edge(this, end, dist, num);
  }
  
  public void addEdge(Edge e) {
	  this.edges.add(e);
  }
  
  public Edge getEdge(int i) {
	  return edges.get(i);
  }
  
  public List<Edge> getEdges() {
	  return this.edges;
  }
  
  public void visit(boolean tf) {
	  this.visited = tf;
  }
  
  public boolean ifVisit() {
	  return this.visited;
  }
  
  public boolean checkVisits() {
	  for (int i = 0; i < edges.size(); i++) {
		  if (!this.getEdge(i).checkVisited()) {
			  return false;
		  }
	  }
	  return true;
  }

}
