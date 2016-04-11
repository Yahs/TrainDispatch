
public interface Controller {

	public boolean isBlocked(Train train);
	public void move (Train train, Double dt);
	public void lockPath (Train train);
	public void unlockPath (Train train);
	
}
