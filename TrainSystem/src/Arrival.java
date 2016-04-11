/*
 * Based off of the particle simulator from the book Algorithms
 * by Robert Sedgewick and Kevin Wayne. Seen on Page 861 we are
 * using an Arrival as an event within the simulator instead of
 * a collision. 
 * 
 */


public class Arrival implements Comparable<Arrival> {
	private final double time;
	private final Train train;
	private final Station station;
	
	public Arrival (double time, Train train, Station station) {
		this.time = time;
		this.train = train;
		this.station = station;
	}
	
	@Override
	public int compareTo(Arrival that) {
		if (time < that.time) return -1;
		else if (time > that.time) return +1;
		else return 0;
	}
	
	public boolean isValid() {
		if (train == null || station == null)
			return false;
		return true;
	}
	
	public Station getStation () { return station; }
	public Train getTrain() { return train; }
	public double getTime() { return time; }
	
}
