
import java.awt.Point;
import java.util.ArrayList;


public class Station extends Point
{
	private String stationName;
	private int maxCapacity;
	
	private ArrayList<Train> trains = new ArrayList<Train>();
	private ArrayList<Track> adj = new ArrayList<Track>();
	int capacity = 0;
	
	public Station (String name, int cap, Point location) 
	{
		super (location);
		stationName = name;
		maxCapacity = cap;
	}
	
	public void addTrain (Train t) {
		trains.add(t);
		capacity++;
	}
	
	public void removeTrain (Train t) {
		if (trains.contains(t)) {
			trains.remove((trains.indexOf(t)));
			capacity--;
		}
	}
	
	public void setAdj (Track t) { adj.add(t); }
	public int getCapacity() { return maxCapacity; }
	public String getName () { return stationName; }
	public ArrayList<Track> getAdj () { return adj; }	
	
	@Override
	public void setLocation (Point p) {
		super.setLocation(p);
		for (Track t: adj) {
			t.updateLocation();
		}
	}
	
	@Override
	public String toString () {
		String output = "Station: " + stationName +
						"\nLocation: (" + x + ", " + y + ")" +
						"\nMax Capacity: " + maxCapacity +
						"\nStationed Trains:\n";
		
		for (int i = 0; i < trains.size(); i++)
			output += "Train " + (i+1) + ":\n" + trains.get(i).toString() + "\n";
		
		return output;
	}
}