
public class Edge {

	private int start;
	private int end;
	private int distance;
	
	public Edge (int start, int end, int distance) {
		this.start = start;
		this.distance = distance;
		this.end = end;
	}
	
	public int getStart() {
		return start;
	}
	public int getEnd() {
		return end;
	}
	public int getCost() {
		return distance;
	}
}
