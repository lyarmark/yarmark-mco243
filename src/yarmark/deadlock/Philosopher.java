package yarmark.deadlock;

public class Philosopher extends Thread {

	private Fork fRight;
	private Fork fLeft;
	private int number;
	private Philosopher higherNeighbor;
	private Philosopher lowerNeighbor;

	Philosopher(Fork f1, Fork f2, int number) {
		this.number = number;
		this.fRight = f1;
		this.fLeft = f2;
	}

	public void run() {
		while (true) {
			think();
			if (this.number == 3) {
				eat();
			} else {
				eat();
			}
		}
	}

	public void eat() {
		System.out.println(this.number + " Trying to pick up fork: " + fRight.toString());
		// synchronize of f1 so no one else can use it
		synchronized (fRight) {
			if (this.requestRightFork(lowerNeighbor)) {
				// if you can get f1, get f2 also
				System.out.println(this.number + " got fork " + fRight.toString());
				System.out.println(this.number + " Trying to pick up fork: " + fLeft.toString());
				synchronized (fLeft) {
					if (this.requestLeftFork(higherNeighbor)) {
						System.out.println(this.number + " got fork " + fLeft.toString());
						System.out.println(this.number + " eating, forks dirty");
						fRight.setClean(false);
						fLeft.setClean(false);
						waitForAFewSeconds();
					}
				}
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

	public boolean requestRightFork(Philosopher lowerPhilFork) {
		if (!lowerPhilFork.fLeft.isClean()) {
			lowerPhilFork.cleanFork(fLeft);
			return true; // you can use my fork
		}
		return false; // you can't use my fork
	}

	public boolean requestLeftFork(Philosopher higherPhilFork) {
		if (!higherPhilFork.fRight.isClean()) {
			higherPhilFork.cleanFork(fRight);
			return true; // you can use my fork
		}
		return false; // you can't use my fork
	}

	private void cleanFork(Fork fork) {
		fork.setClean(true);
	}

	public void setNeighbors(Philosopher higher, Philosopher lower) {
		this.higherNeighbor = higher;
		this.lowerNeighbor = lower;
	}
}
