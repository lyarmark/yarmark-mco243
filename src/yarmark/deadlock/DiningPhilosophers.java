package yarmark.deadlock;

public class DiningPhilosophers {
	public static void main(String[] args) {

		Fork f1 = new Fork(1);
		Fork f2 = new Fork(2);
		Fork f3 = new Fork(3);
		Fork f4 = new Fork(4);
		Fork f5 = new Fork(5);

		Philosopher a = new Philosopher(f1, f2, 'a');
		Philosopher b = new Philosopher(f2, f3, 'b');
		Philosopher c = new Philosopher(f3, f4, 'c');
		Philosopher d = new Philosopher(f4, f5, 'd');
		Philosopher e = new Philosopher(f5, f1, 'e');

		a.start();
		b.start();
		c.start();
		d.start();
		e.start();
	}
}
