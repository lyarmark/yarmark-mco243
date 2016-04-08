package yarmark.deadlock;

public class Fork {

	private int number;
	private boolean inUse;

	public Fork(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public String toString() {
		return String.valueOf(number);
	}

	public boolean isInUse() {
		return inUse;
	}

	public void setInUse(boolean b) {
		this.inUse = b;
	}
}
