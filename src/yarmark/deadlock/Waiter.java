package yarmark.deadlock;

public class Waiter {

	// return true if you get both forks
	// needs to be synchronized so 2 phil can't try to pick up fork at the same
	// time
	public synchronized boolean tryToEat(Fork a, Fork b) {
		if (!a.isInUse() && !b.isInUse()) {
			a.setInUse(true);
			b.setInUse(true);
			return true;
		}
		return false;
	}

}
