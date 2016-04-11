import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.Timer;

/*
 * Heavily modified from the particle simulator from the book Algorithms
 * by Robert Sedgewick and Kevin Wayne.
 * 
 */


public class TrainSimulator implements ActionListener {

	private Controller con = null;
	private Timer timer = new Timer(500, this);
	private MapPanel mapPanel;
	private Map map;
	private ArrayList<Train> trains;
	private MinPQ<Arrival> pq;
	private double t = 0;
	private double delay;
	
	private double averageArrivalTime = 0;
	
	public TrainSimulator(MapPanel mapPanel) {
		this.mapPanel = mapPanel;
		map = mapPanel.getMap();
		timer.setRepeats(true);
	}
	
	/* 
	 * Creates the map and adds all the paths to the trains. 
	 */
	public void simulate() {
		pq = new MinPQ<Arrival>();
		
		map.createMap();

		trains = map.getTrains();
		for (int i = 0; i < trains.size(); i++) {
			Station a = trains.get(i).getStartLocation();
			Station b = trains.get(i).getDestination();
			LinkedList<Track> path = map.getPath(map.getNodeIndex(a), map.getNodeIndex(b));
			trains.get(i).setPath(path);
			predictArrival(trains.get(i));
		}
		
		con = new BaseCaseController();
		timer.start();
	}
	
	public void redraw () {
		String msg = "";

		for (Train train: map.getTrains()) {
			msg += train + "\n\n";
		}
		
		mapPanel.setInfo(msg);
		mapPanel.revalidate();
		mapPanel.getCanvas().repaint();
	}
	
	public void baseCaseLoop() {
		int errorMargin = 20;
		Arrival event = pq.delMin();
		
		if (event.isValid()) {
		    Train train = event.getTrain();
			Station station = event.getStation();
			
			con.lockPath(train);
						
			for (int i = 0; i < trains.size(); i++){
				con.move(trains.get(i), event.getTime() - t);
			}
						
			t = event.getTime();
					
			
			if (station.distance(train) < errorMargin) {
				if (train.hasArrived()) {
					con.unlockPath(train);
					System.out.println("Train " + trains.indexOf(train) + 
							" travelled " + train.getTotalLength() + "m traveling at " +
							train.getSpeed() + "mph and Arrived in " + (
							t - train.getStartTime()) + " hours ");
					averageArrivalTime += t - train.getStartTime();
				}
				train.next();
			} 
			
			predictArrival(train);
		}
	}
	
	private void predictArrival(Train train) 
	{
		if (train == null || train.nextStation() == null) return;
		double distance = train.getDistance();
		double dt = distance / train.getSpeed();
		if (train.getDelay() != 0)
			pq.insert(new Arrival(t+train.getDelay(), train, train.nextStation()));
		else
			pq.insert(new Arrival(t + dt, train, train.nextStation()));
		train.setDelay(0);
	}

	public void pause () {
		timer.stop();
	}
	
	public void play() {
		timer.setDelay(1000);
		timer.start();
	}
	
	public void speedUp() {
		System.out.println(timer.getDelay());
		timer.setDelay(timer.getDelay()/5);
	}
	
	public void slowDown() {
		System.out.println(timer.getDelay());
		timer.setDelay(timer.getDelay()*5);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer) {
			if (pq.isEmpty()) {
				timer.stop();
				System.out.println("DONE");
				System.out.println("Average arrival Time: " + averageArrivalTime/trains.size());
			} else {
				baseCaseLoop();
				redraw();
			}
		}
	}	
}
