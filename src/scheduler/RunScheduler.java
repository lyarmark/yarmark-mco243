package scheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RunScheduler {
	public static void main(String[] args) {

		List<Job> jobs = Arrays.asList(new Job("1", Priority.High, JobType.Computation, 100), new Job("2",
				Priority.Low, JobType.IO, 200), new Job("3", Priority.Medium, JobType.Computation, 600), new Job("4",
				Priority.High, JobType.IO, 100), new Job("5", Priority.Low, JobType.IO, 332), new Job("6",
				Priority.Low, JobType.Computation, 100), new Job("7", Priority.Medium, JobType.Computation, 400),
				new Job("8", Priority.Medium, JobType.Computation, 402), new Job("9", Priority.High, JobType.IO, 456),
				new Job("10", Priority.High, JobType.Computation, 100), new Job("11", Priority.Low,
						JobType.Computation, 560), new Job("12", Priority.High, JobType.IO, 241));

		SPFJobScheduler scheduler = new SPFJobScheduler(new ArrayList<Job>(jobs), new PriorityJobComparator());

		scheduler.run();
		System.out.println(String.format("numsJobsCompleted = %d totalTime = %d", scheduler.getNumJobsCompleted(),
				scheduler.getTotalTime()));
	}
}
