package ghostbusters;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Gatekeeper extends Thread {
	int admitted = 0;
	Random rand = new Random();
	boolean running = true;

	public Callable<Integer> askAdmitted() {
		return new Callable<Integer>(){
			@Override
			public Integer call() throws Exception {
				synchronized(Gatekeeper.this) {
//					System.out.println("I got "+admitted);
					return admitted;
				}
			}
		};
	}
	
	public Runnable askPause() {
		return new Runnable() {
			@Override
			public void run() {
				synchronized(Gatekeeper.this) {
//					if (running) 
//						System.out.println("Ok, stopping now");
					running = false;
				}
			}
		};
	}
	
	public Runnable askGo() {
		return new Runnable() {
			@Override
			public void run() {
				synchronized(Gatekeeper.this) {
//					if (!running)
//						System.out.println("Ok, here we GO!");
					running = true;
				}
			}
		};
	}
	
	private void justSleepDammit(long milli) {
		try {
			Thread.sleep(10);
		} catch (InterruptedException _) {	}
	}
	
	private synchronized boolean shouldAdmit() {
		return running && rand.nextInt(10) == 0;
	}
	
	public void run() {
		System.out.println("Gotcha!");
		while (true) {
			justSleepDammit(10);
			if (shouldAdmit()) {
				hereHeComes();
			}
		}
	}

	private synchronized void hereHeComes() {
		admitted++;
	}

}
