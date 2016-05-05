package yarmark.scheduler;

import java.util.Comparator;
import java.util.List;

public class RoundRobinJobScheduler extends JobScheduler {

	public RoundRobinJobScheduler(List<Job> jobs, Comparator<Job> comparator) {
		this.jobs = jobs;
		this.comparator = comparator;
	}

	@Override
	public void run() {
		Job lastJob = null;
		while (!jobs.isEmpty()) {
			Job job = jobs.get(0);
			int actualTimeSlice = executeJob(job);
			totalTime += actualTimeSlice;

			if (actualTimeSlice > job.getTimeLeftToRun()) {
				numJobsCompleted++;
				jobs.remove(0);
			} else {
				jobs.remove(0);
				jobs.add(job);
			}
			// context switch whether the previous job finished or not
			if (job != lastJob) {
				totalTime += OVERHEAD;
			}
		}
	}
}
