package scheduler;

import java.util.Comparator;

public class FIFOJobComparator implements Comparator<Job> {

	@Override
	public int compare(Job a, Job b) {
		//always return 0 for no changes
		return 0;
	}

}
