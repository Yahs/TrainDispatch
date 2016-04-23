public class Edge {

	private Station start;
	private Station end;
	private int distance;
	private double angle;
	
	public Edge (Station start, Station end, int distance) {
		this.start = start;
		this.distance = distance;
		this.end = end;
		this.angle = start.getAngle(end);
	}
	
	public int getStart() {
		return this.start.getNum();
	}
	public int getEnd() {
		return this.end.getNum();
	}
	public int getCost() {
		return this.distance;
	}
	
	public double getAngle() {
		return this.angle;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.getStart() + "\t\t\t" + this.getEnd() + "\t\t\t"
				+ this.getCost());
		return new String(sb);
	}
}