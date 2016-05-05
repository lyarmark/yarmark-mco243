package yarmark.scheduler;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public abstract class JobScheduler implements Runnable {
	static final int OVERHEAD = 3;
	static final Random RANDOM = new Random();
	private static final int TIME_SLICE = 10;;

	List<Job> jobs;
	Comparator<Job> comparator;

	int numJobsCompleted;
	int totalTime;

	public int getTotalTime() {
		return totalTime;
	}

	public int getNumJobsCompleted() {
		return numJobsCompleted;
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
