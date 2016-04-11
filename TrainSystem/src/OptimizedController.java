import java.util.LinkedList;


public class OptimizedController implements Controller {
	
	public OptimizedController () {
	}
	
	
	//IDEA
	/*
	 * not blocked moving in the same direction ensure just make sure its a certain distance away
	 * if opposite direction ensure reach destination first or wait
	 * 
	 */
	
	@Override
	public boolean isBlocked(Train train) {
		for (Track track: train.getPath()) {
			if (track.isLocked()) {
				for (Train trainlock: track.getLocks()) {
				}
			}
		}
		return false;
	}
	
	/*
	 * Added: unlocks the track behind its path. 
	 */
	@Override 
	public void move (Train train, Double dt) {
		if (train.isLockedIn()) {
			train.move(dt);
			LinkedList<Track> path = train.getPath();
			for (int i = 0; i < train.getCurrentTrack(); i++) {
				path.get(i).removeLock(train);
			}
		} else {
			train.addDelay(dt);
		}
	}
	
	@Override
	public void lockPath (Train train) {
		if (!isBlocked(train) && !train.hasArrived() && !train.isLockedIn()) {
			for (Track t: train.getPath()) {
				t.addLock(train);
				t.color = train.color.darker();
			}
			train.lockedIn(true);
		}
	}
	
	@Override
	public void unlockPath (Train train) {
		for (Track t: train.getPath()) {
			t.removeLock(train);
			t.color = null;
		}
	}	
	
}
