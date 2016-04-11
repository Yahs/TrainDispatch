import java.awt.Color;
import java.util.Random;

/*
 *  This generator uses the method of generating a exponential random variable
 *  as defined by Michael Baron within his book Probability and statistics
 *  for computer scientists.
 */

public class TrainGenerator {
	
	private final Color colors[] = {
	        Color.orange, Color.DARK_GRAY, Color.pink,
	        Color.cyan, Color.magenta, Color.yellow, 
	        Color.GREEN, Color.BLUE, Color.LIGHT_GRAY, 
	        Color.red, Color.WHITE};
	
	private final Random rand = new Random();
	private final Map map;
	private double t = 0;
	
	public TrainGenerator(Map p) {
		map = p;
	}
	
	public void generate(int numOfTrains, double lambda) {
		for (int i = 1; i <= numOfTrains; i++) {
			int start = 0;
			int end = 0;
			
			while (start == end) {
				start = rand.nextInt(map.getNodes().size());
				end = rand.nextInt(map.getNodes().size());
			}
			
			//Exponential random variable for time between events.
			double x = -(1.0/lambda) * Math.log(rand.nextDouble());
			t += x;
			
			System.out.println(t);
			
			Train train = new Train(100, t, map.getNode(start), map.getNode(end));
			train.color = colors[i%colors.length];
			map.addTrain(train);
		}
		

	}
	
	/*Simple test to make sure the simulator is working the way it is supposed to*/
	public void test () {
		Train t = new Train(100, 0, map.getNode(6), map.getNode(3));
		t.color = Color.orange;
		map.addTrain(t);
		t = new Train(100, 0, map.getNode(4), map.getNode(0));
		t.color = Color.green;
		map.addTrain(t);		
	}
}

