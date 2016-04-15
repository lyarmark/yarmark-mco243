package scheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class JobScheduler implements Runnable {

	// max amount of time each job runs for
	private static final int TIME_SLICE = 10;

	// amount of time it takes to switch threads
	private static final int OVERHEAD = 3;
	private static final Random RANDOM = new Random();;

	private List<Job> jobs;
	private Comparator<Job> comparator;

	private int numJobsCompleted;
	private int totalTime;

	public JobScheduler(List<Job> jobs, Comparator<Job> comparator) {
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

	public int getNumJobsCompleted() {
		return numJobsCompleted;
	}

	public int getTotalTime() {
		return totalTime;
	}

	// returns the amount of time used by the job
	private int executeJob(Job job) {

		job.setState(JobState.Running);

		job.setLastRunAtTime(System.currentTimeMillis());
		int timeLeftToRun = job.getTimeLeftToRun();
		int actualTimeSlice = computeActualTimeSlice(job);
		job.decrementTimeLeftToRun(actualTimeSlice);

		if (job.isFinished()) {
			jobs.remove(0);
			numJobsCompleted++;
		} else {
			job.setState(JobState.Ready);
		}
		return actualTimeSlice;
	}

	private int computeActualTimeSlice(Job job) {
		if (job.getType() == JobType.IO) {
			return Math.min(RANDOM.nextInt(TIME_SLICE), job.getTimeLeftToRun());
		} else {
			return Math.min(job.getTimeLeftToRun(), TIME_SLICE);
		}
	}

	public static void main(String[] args) {
		List<Job> jobs = Arrays.asList(new Job("1", Priority.High, JobType.Computation, 100), new Job("2",
				Priority.Low, JobType.IO, 200), new Job("3", Priority.Medium, JobType.Computation, 600), new Job("4",
				Priority.High, JobType.IO, 100), new Job("5", Priority.Low, JobType.IO, 332), new Job("6",
				Priority.Low, JobType.Computation, 100), new Job("7", Priority.Medium, JobType.Computation, 400),
				new Job("8", Priority.Medium, JobType.Computation, 402), new Job("9", Priority.High, JobType.IO, 456),
				new Job("10", Priority.High, JobType.Computation, 100), new Job("11", Priority.Low,
						JobType.Computation, 560), new Job("12", Priority.High, JobType.IO, 241));

		JobScheduler scheduler = new JobScheduler(new ArrayList<Job>(jobs), new PriorityJobComparator());
		scheduler.run();
		System.out.println(String.format("numsJobsCompleted=%d totalTime=%d", scheduler.getNumJobsCompleted(),
				scheduler.getTotalTime()));
	}
}
