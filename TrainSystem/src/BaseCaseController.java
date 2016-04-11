
public class BaseCaseController implements Controller {
	
	public BaseCaseController () {
	}
	
	@Override
	public boolean isBlocked(Train train) {
		for (Track t: train.getPath()) {
			if (t.isBlocked(train)) {
				return true;
			}
		}
		return false;
	}
	
	
	@Override 
	public void move (Train train, Double dt) {
		if (train.isLockedIn()) {
			train.move(dt);
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
