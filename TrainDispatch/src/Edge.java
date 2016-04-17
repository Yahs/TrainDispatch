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
		return this.start;
	}
	public int getEnd() {
		return this.end;
	}
	public int getCost() {
		return this.distance;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.getStart() + "\t\t\t" + this.getEnd() + "\t\t\t"
				+ this.getCost());
		return new String(sb);
	}
}