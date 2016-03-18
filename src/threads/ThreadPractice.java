package threads;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPractice {
	// static int total = 0;

	public static void main(String[] args) throws IOException, InterruptedException {

		final ExecutorService service = Executors.newFixedThreadPool(6);
		final AtomicInteger total = new AtomicInteger(0);
		final AtomicBoolean allowed = new AtomicBoolean(true);

		ActionListener l = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (allowed.get()) {
					allowed.set(false);
					Runnable runnable = new Runnable() {
						public void run() {
							try {
								// LONG RUNNING OPERATION
								// if you set allowed to true here, and the
								// operation throws an exception, it will never
								// get set to true
								// can catch the exception and set it in the
								// catch
								// can move the set to after the run method
								// instead, use finally
								// allowed.set(true);
							} catch (Exception e) {
								e.printStackTrace();
								// allowed.set(true);
							}
							// allowed.set(true);
							finally {
								allowed.set(true);
							}
						}
					};
					service.equals(runnable);
				}
			}
		};

		for (int i = 0; i < 10000; i++) {
			Runnable runnable = new Runnable() {
				public void run() {
					total.incrementAndGet();
				}

			};
			service.execute(runnable);
		}
		service.shutdown();
		service.awaitTermination(10, TimeUnit.SECONDS);
		System.out.print(total);

	}
	// a synchronized method ensures that only one thread can run this method a
	// time
	// but if the method might be long, so that's not efficient
	// public/* synchronized */static void add() {
	// total++;
	// total.incrementAndGet();
	// }

}