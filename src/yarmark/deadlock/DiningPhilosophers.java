package yarmark.deadlock;

public class DiningPhilosophers {
	public static void main(String[] args) {

		Fork f1 = new Fork(1);
		Fork f2 = new Fork(2);
		Fork f3 = new Fork(3);
		Fork f4 = new Fork(4);
		Fork f5 = new Fork(5);

		Philosopher a, b, c, d, e;
		a = new Philosopher(f1, f2, 1);
		b = new Philosopher(f2, f3, 2);
		c = new Philosopher(f3, f4, 3);
		d = new Philosopher(f4, f5, 4);
		e = new Philosopher(f1, f5, 5);

		a.setNeighbors(e, b);
		b.setNeighbors(c, a);
		c.setNeighbors(d, b);
		d.setNeighbors(e, c);
		e.setNeighbors(a, d);

		a.start();
		b.start();
		c.start();
		d.start();
		e.start();
	}
}
