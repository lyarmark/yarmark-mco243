package scheduler;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FIFOJobScheduler extends JobScheduler {

	// max amount of time each job runs for
	private static final int TIME_SLICE = 10;

	// amount of time it takes to switch threads

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

	// returns the amount of time used by the job
	public int executeJob(Job job) {

		job.setState(JobState.Running);
		job.setLastRunAtTime(System.currentTimeMillis());
		int actualTimeSlice = waitForJobToRun(job);
		job.decrementTimeLeftToRun(actualTimeSlice);

		if (job.isFinished()) {
			jobs.remove(0);
			numJobsCompleted++;
		} else {
			job.setState(JobState.Ready);
		}
		return actualTimeSlice;
	}

	public int waitForJobToRun(Job job) {
		if (job.getType() == JobType.IO) {
			return Math.min(RANDOM.nextInt(TIME_SLICE), job.getTimeLeftToRun());
		} else {
			return Math.min(job.getTimeLeftToRun(), TIME_SLICE);
		}
	}
}
