package yarmark.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DownloadLotsOfImages {
	int totalDownloaded = 0;
	int totalProcessed = 0;

	public void downloadFile() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		synchronized (this) {
			totalDownloaded++;
		}
	}

	public void processFile() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		synchronized (this) {
			totalProcessed++;
		}
	}

	public DownloadLotsOfImages() throws InterruptedException {
		ExecutorService service = Executors.newFixedThreadPool(6);

		for (int i = 0; i < 10000; i++) {
			Runnable runnable = new Runnable() {
				public void run() {
					downloadFile();
					processFile();
				}
			};
			service.execute(runnable);
		}
		service.shutdown();
		service.awaitTermination(12, TimeUnit.DAYS);
		System.out.println(totalDownloaded + ", " + totalProcessed);

	}

	public static void main(String[] args) throws InterruptedException {
		DownloadLotsOfImages down = new DownloadLotsOfImages();
		down.downloadFile();
		down.processFile();
	}

}
