import java.awt.Color;
import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;


public class Train extends Point {

	private double speed;
	private double startTime;
	private Station start;
	private Station end;
	
	private LinkedList<Track> path;
	private int currentTrack;
	private Station currentStation;
	public Color color;
	
	//estimated time of arrival
	private double eta;
	private double delay = 0;
	
	//set to true if its path is available
	private boolean lockedIn = false;
	
	public Train (double s, double t, Station a, Station b) {
		super((Point)a);
		speed = s;
		startTime = t;
		start = a;
		end = b;
		currentStation = a;
		currentTrack = 0;
		delay = startTime;
	}
	
	public void setPath (LinkedList<Track> path) {
		this.path = path;
		eta = startTime + getTotalLength() / speed;
	}
	
	public void next() {
		if(nextStation() != null){
			currentStation.removeTrain(this);
			currentStation = nextStation();
			currentStation.addTrain(this);
			currentTrack++;
		}
	}
	
	/*
	 * Calculates the station before a collision and returns 
	 * the delay needed for the train to stay at the station
	 * until the other has passed it left.
	 */
	public double getCollision (Train train) {
		return 0;
	}
	
	/*
	 * Returns the estimated time to arrive at the final destination
	 */
	public double getETA() {
		return eta;
	}
	
	/*
	 * Returns the estimated time to arrive at a specific station
	 */
	public double getEta (Station des) {
		double length = getDistance();
		Station nextStation = path.get(currentTrack).getNeighbor(currentStation);
		
		for (int i = currentTrack+1; i < path.size(); i++) {
			if (nextStation == des) {
				break;
			}
			Track track = path.get(currentTrack);
			length += track.getLength();
			nextStation = track.getNeighbor(nextStation);
		}
		
		return length / speed;
	}
	
	/*
	 * Returns the time needed before the track can unlock.
	 */
	public double getTimeToUnlock (Track des) {
		double length = getDistance();
		int index = currentTrack;
		
		if (path.get(index) == des)
			return length / speed;
		
		while (index < path.size() && path.get(index++) != des) {
			length += path.get(index).getLength();
		}
		
		length += path.get(index).getLength();
		return length / speed;
	}
	
	//this probably needs to be improved;
	public boolean sameDirection(Train train) {
		Station[] sequence1 = getNodeSequence();
		Station[] sequence2 = train.getNodeSequence();
		
		for (int i = 0; i < sequence1.length; i+=2) {
			for (int j = 0; j < sequence2.length; j+=2) {
				if (sequence1[i] == sequence2[i] && sequence2[i+1] != sequence2[i+1]) 
					return false;
			}
		}
		return true;
	}
	
	public Station[] getNodeSequence() {
		Station sequence[] = new Station[path.size()+1];
		Station temp = start;
		int i = 0;
		for (; i < path.size(); i++) {
			sequence[i] = temp;
			temp = path.get(i).getNeighbor(temp);
		}
		sequence[i+1] = temp;
		return sequence;
	}
	
	public void addDelay(double delay) {
		eta += delay;
		this.delay += delay;
	}
	public void setDelay (double t) {
		delay = t;
	}
	
	//distance to the next station
	public double getDistance() {
		Track current = path.get(currentTrack);
		return current.getLength() - current.getDistanceOnTrack(currentStation, (Point)this);
	}
	
	public Station nextStation () {
		if (currentTrack < path.size())
			return path.get(currentTrack).getNeighbor(currentStation);
		else
			return null;
	}
	
	public Track getTrack () {
		if (currentTrack < path.size()) {
			return path.get(currentTrack);
		}
		return null;
	}
	
	
	public void move (double dt) {
		if (currentTrack < path.size()) {
			Track t = path.get(currentTrack);
			double distance = speed * dt + t.getDistanceOnTrack(currentStation, (Point)this);
			Point nextPoint = t.getPos(currentStation, distance);
			if (distance > t.getLength()) {
				setLocation(nextStation());
			} else if (nextPoint != null) { setLocation(nextPoint); }
		}
	}
	
	public double getSpeed () { return speed; }
	public double getStartTime() { return startTime; }
	public Station getStartLocation() { return start; }
	public Station getDestination() { return end; }
	public LinkedList<Track> getPath () {return path; }
	public double getDelay() { return delay; }
	public int getCurrentTrack() { return currentTrack; }
	
	public double getTotalLength () { 
		double total = 0;
		for (Track t: path)
			total += t.getLength();
		return total;
	}
	
	public boolean hasArrived() {
		if (this.distance(end) < 10)
			return true;
		return false;
	}
	
	public boolean isLockedIn() { return lockedIn;	}
	public void lockedIn (boolean l) { lockedIn = l;};
	
	
	@Override
	public String toString() {
		String msg = "Start: " + start.getName() +
					 "\nDestination: " + end.getName() +
					 "\nDistance: " + getTotalLength() +
					 "\nSpeed: " + speed + "mph" +
					 "\nETA: " + eta + 
					 "\nArrived:" + hasArrived() + "\n";
		return msg;
	}
	
	
}
