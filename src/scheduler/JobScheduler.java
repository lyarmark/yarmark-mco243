package scheduler;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public abstract class JobScheduler implements Runnable {
	static final int OVERHEAD = 3;
	static final Random RANDOM = new Random();;

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

	public abstract void run();

	public abstract int executeJob(Job job);

	public abstract int waitForJobToRun(Job job);

}
