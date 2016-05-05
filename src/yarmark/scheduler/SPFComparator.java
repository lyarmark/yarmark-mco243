
package yarmark.scheduler;

import java.util.Comparator;

public class SPFComparator implements Comparator<Job> {

	public int compare(Job a, Job b) {
		if (a.getTimeLeftToRun() > b.getTimeLeftToRun()) {
			return 1;
		}
		if (a.getTimeLeftToRun() < b.getTimeLeftToRun()) {
			return -1;
		} else {
			return 0;
		}
	}
}
