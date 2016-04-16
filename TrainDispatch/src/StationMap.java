import java.util.ArrayList;
import java.util.List;

public class StationMap<Key extends Comparable<Key>> {
	
	private List<Station> stations= new ArrayList<Station>();
	private int numOfStations;
	
	public StationMap() {
		
	}
	
	public StationMap(int num) {
		for (int i = 0; i < num; i++) {
			this.stations.add(new Station(i));
		}
		this.numOfStations = num;
	}
	
	public StationMap(List<Station> list) {
		this.stations = list;
		this.numOfStations = list.size();
	}
	
	public Station callStation(int num) {
		return this.stations.get(num);
	}
	
	

}
