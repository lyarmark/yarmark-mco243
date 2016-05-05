package yarmark.scheduler;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FIFOJobScheduler extends JobScheduler {

	public FIFOJobScheduler(List<Job> jobs, Comparator<Job> comparator) {
		this.jobs = jobs;
		this.comparator = comparator;
	}

	@Override
	public void run() {
		Job lastJob = null;
		while (!jobs.isEmpty()) {
			Collections.sort(jobs, comparator);
			Job job = jobs.get(0);
			int actualTimeSlice = executeJob(job);
			totalTime += actualTimeSlice;
			if (job != lastJob) {
				totalTime += OVERHEAD;
			}
		}
	}
}
