package yarmark.weather;

import java.util.concurrent.LinkedBlockingQueue;

public class NotifyWeatherThread extends Thread {

	// volatile tells the OS this variable needs to be shared btw. different threads
	//don't need the volatile string b/c we are using the queue
	//private volatile String badWeather;
	private LinkedBlockingQueue<String> queue;

	public NotifyWeatherThread(LinkedBlockingQueue<String> queue) {
		this.queue = queue;
	}

	public void run() {

		while (true) {
			try {
				// will block if nothing there
				String message = queue.take();
				soundAlarm(message);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private void soundAlarm(String message) {
		// for all subscribers to this weather service
		for (int i = 0; i < 1000000; i++) {
			// send an email
		}
		//badWeather = null;
	}
}
