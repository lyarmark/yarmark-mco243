package yarmark.deadlock;

public class Fork {

	private int number;
	private boolean clean;

	public Fork(int number) {
		this.number = number;
	}

	public boolean isClean() {
		return clean;
	}

	public void setClean(boolean clean) {
		this.clean = clean;
	}

	public int getNumber() {
		return number;
	}

	public String toString() {
		return String.valueOf(number);
	}
}
