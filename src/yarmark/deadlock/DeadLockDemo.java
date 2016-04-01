package yarmark.deadlock;

public class DeadLockDemo {

	public synchronized static void neverReturn() {
		// it is always sleeping
		try {
			Thread.sleep(1000000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized static void notGoingToHappen() {
		System.out.println("Never prints");
	}

	public static void main(String[] args) {
		new Thread() {
			public void run() {
				DeadLockDemo.neverReturn();
			}
		}.start();

		new Thread() {
			public void run() {
				DeadLockDemo.notGoingToHappen();
			}
		}.start();

	}
}
