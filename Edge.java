public class Edge {

	private Station start;
	private Station end;
	private int distance;
	private boolean visited = false;
	private int num;
	
	public Edge (Station start, Station end, int distance, int number) {
		this.start = start;
		this.distance = distance;
		this.end = end;
		this.num = number;
		this.start.addEdge(this);
		this.end.addEdge(this);
	}
	
	public Station getStart() {
		return this.start;
	}
	public Station getEnd() {
		return this.end;
	}
	public Station getStart(Station s) {
		if (s.getNum() == this.getNum()) {
			return this.end;
		}
		return this.start;
	}
	public Station getEnd(Station s) {
		if (s.getNum() == this.getNum()) {
			return this.start;
		}
		return this.end;
	}
	public int getCost() {
		return this.distance;
	}
	
	public int getNum() {
		return this.num;
	}
	
	public boolean checkVisited() {
		return this.visited;
	}
	
	public void visit(boolean tf) {
		this.visited = tf;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.getStart() + "\t\t\t" + this.getEnd() + "\t\t\t"
				+ this.getCost());
		return new String(sb);
	}
}