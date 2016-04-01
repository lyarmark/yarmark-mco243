package yarmark.deadlock;

public class Philosopher extends Thread {

	private Fork f1;
	private Fork f2;
	private char name;

	public Philosopher(Fork f1, Fork f2, char c) {
		this.name = c;
		this.f1 = f1;
		this.f2 = f2;
	}

	public void run() {
		while (true) {
			think();
			eat();
		}
	}

	public void eat() {
		System.out.println(this.name + " Trying to pick up fork: " + f1.toString());
		// synchronize of f1 so no one else can use it
		synchronized (f1) {
			// if you can get f1, get f2 also
			System.out.println(this.name + " Trying to pick up fork: " + f2.toString());
			synchronized (f2) {
				System.out.println(this.name + " eating");
				waitForAFewSeconds();
			}
		}
	}

	public void waitForAFewSeconds() {
		try {
			Thread.sleep((long) (Math.random() * 10000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void think() {
		waitForAFewSeconds();
	}
}
