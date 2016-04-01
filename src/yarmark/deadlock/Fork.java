package yarmark.deadlock;

public class Fork {

	private int number;

	public Fork(int number) {
		this.number = number;
	}

	public String toString() {
		return String.valueOf(number);
	}
}
