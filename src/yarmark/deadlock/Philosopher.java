package yarmark.deadlock;

import java.util.logging.Logger;

public class Philosopher extends Thread {

	private Fork fRight;
	private Fork fLeft;
	private int number;

	private Waiter waiter;
	private static final Logger LOGGER = Logger.getLogger(Philosopher.class.getName());

	Philosopher(Fork f1, Fork f2, int number, Waiter waiter) {
		this.number = number;
		this.fRight = f1;
		this.fLeft = f2;
		this.waiter = waiter;
	}

	public void run() {
		while (true) {
			eat();
			think();
		}
	}

	public void eat() {
		LOGGER.info(this.toString() + " attempting to eat");
		if (waiter.tryToEat(fRight, fLeft)) {
			LOGGER.info(this.toString() + "eating");
			waitForAFewSeconds();
			fRight.setInUse(false);
			fLeft.setInUse(false);
		}
		LOGGER.info(this.toString() + " done eating");
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
