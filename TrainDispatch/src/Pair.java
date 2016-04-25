
public class Pair {

	private Station start;
	private Station end;
	
	public Pair() {
		
	}
	
	public Pair(Station s, Station e) {
		this.start = s;
		this.end = e;
	}
	
	public Station getStart() {
		return this.start;
	}
	
	public Station getEnd() {
		return this.end;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.getStart().getNum() + "\t\t\t" + 
				this.getEnd().getNum() + "\t\t\t\n");
		return new String(sb);
	}
}
