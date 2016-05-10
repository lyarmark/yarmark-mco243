package yarmark.weather;

import java.util.concurrent.LinkedBlockingQueue;

public class Main {

	public static void main(String[] args) {
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		NotifyWeatherThread notifyWeather = new NotifyWeatherThread(queue);
		CheckWeatherThread checkWeather = new CheckWeatherThread(queue);

		notifyWeather.start();
		checkWeather.start();
		
		//glue to allow these threads to communicate with e/o
		//LinkedBlockingQueue
	}
}
