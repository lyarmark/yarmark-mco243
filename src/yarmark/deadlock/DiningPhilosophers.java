package yarmark.deadlock;

public class DiningPhilosophers {
	public static void main(String[] args) {

		Fork f1 = new Fork(1);
		Fork f2 = new Fork(2);
		Fork f3 = new Fork(3);
		Fork f4 = new Fork(4);
		Fork f5 = new Fork(5);

		Waiter waiter = new Waiter();

		Philosopher a, b, c, d, e;
		a = new Philosopher(f1, f2, 1, waiter);
		b = new Philosopher(f2, f3, 2, waiter);
		c = new Philosopher(f3, f4, 3, waiter);
		d = new Philosopher(f4, f5, 4, waiter);
		e = new Philosopher(f5, f1, 5, waiter);

		a.start();
		b.start();
		c.start();
		d.start();
		e.start();
	}
}
