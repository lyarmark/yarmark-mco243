package scheduler;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SPFJobScheduler extends JobScheduler implements Runnable {

	public SPFJobScheduler(List<Job> jobs, Comparator<Job> comparator) {
		this.jobs = jobs;
		this.comparator = comparator;
	}

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

	// returns the amount of time used by the job
	public int executeJob(Job job) {

		job.setState(JobState.Running);
		job.setLastRunAtTime(System.currentTimeMillis());
		int timeRan = waitForJobToRun(job);
		jobs.remove(0);
		numJobsCompleted++;
		return timeRan;
	}

	@Override
	public int waitForJobToRun(Job job) {
		return job.getTimeLeftToRun();
	}

}
