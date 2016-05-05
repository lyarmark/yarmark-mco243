package yarmark.scheduler;

public class Job {

	private String name;

	private Priority priority;
	private int dynamicPriority;

	private JobState state; // blocked, ready, running
	private int timeLeftToRun;

	private long lastRunAtTime;
	private JobType jobType;

	public Job(String name, Priority priority, JobType jobType, int timeLeftToRun) {
		this.name = name;
		this.priority = priority;
		this.timeLeftToRun = timeLeftToRun;
		this.jobType = jobType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public int getDynamicPriority() {
		return dynamicPriority;
	}

	public void setDynamicPriority(int dynamicPriority) {
		this.dynamicPriority = dynamicPriority;
	}

	public JobState getState() {
		return state;
	}

	public void setState(JobState state) {
		this.state = state;
	}

	public int getTimeLeftToRun() {
		return timeLeftToRun;
	}

	public void setTimeLeftToRun(int timeLeftToRun) {
		this.timeLeftToRun = timeLeftToRun;
	}

	public long getLastRunAtTime() {
		return lastRunAtTime;
	}

	public void setLastRunAtTime(long lastRunAtTime) {
		this.lastRunAtTime = lastRunAtTime;
	}

	public JobType getType() {
		return jobType;
	}

	public void decrementTimeLeftToRun(int actualTimeSlice) {
		timeLeftToRun -= actualTimeSlice;
	}

	public boolean isFinished() {
		return (timeLeftToRun == 0);
	}

}
