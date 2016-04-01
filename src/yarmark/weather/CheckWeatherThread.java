package yarmark.weather;

import java.util.concurrent.LinkedBlockingQueue;

public class CheckWeatherThread extends Thread {

	private LinkedBlockingQueue<String> queue;

	public CheckWeatherThread(LinkedBlockingQueue<String> queue) {
		this.queue = queue;
	}

	public void run() {
		while (true) {
			try {
				// execute every 5 seconds
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			notifyBadWeather();
		}
	}

	private void notifyBadWeather() {
		queue.add("badWeather");
	}

}
