package asynchrony;

import java.util.concurrent.*;
/**
 * This is a straightforward translation of FutureDemo
 * to use java.util.concurrent.FutureTask
 * @author oscar
 *
 */

public class FutureTaskExecutorDemo {
	
	ExecutorService exec = Executors.newFixedThreadPool(3);
	
	public static void main(String args[]) {
		FutureTaskExecutorDemo.demo();
	}
	
	public static void demo() {
		FutureTaskExecutorDemo server = new FutureTaskExecutorDemo();
		System.out.println("Starting FutureDemo.demo()");
		// With some luck, the faster ones may get computed first ...
		// NB: results depend highly on Thread scheduling ...
		startDemoThread(server, 45);
		startDemoThread(server, 35);
		startDemoThread(server, 20);
		startDemoThread(server, 15);
		startDemoThread(server, 5);
		server.shutdown();
	}
	
	private void shutdown() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		exec.shutdown();
	}

	protected static void startDemoThread(final FutureTaskExecutorDemo server, final int n){
		new Thread() {
			public void run() {
				try {
					System.out.println("CALLING fibonacci("+n+")");
					Future<Integer> future = server.service(n);
					System.out.println("GOT future(fibonacci("+n+"))");
					try {
						int val = future.get(2, TimeUnit.SECONDS).intValue();
						System.out.println("GOT fibonacci("+n+") = " + val);
					} catch (TimeoutException e) {
						boolean cancelled = future.cancel(true);
						System.out.println("GOT bored of waiting for fibonacci("+n+"). Answer was "+cancelled);
					}
				} catch(InterruptedException e) {
				} catch(ExecutionException e) {
				}
			}
		}.start();
	}
	
	public Future<Integer> service (final int n) {		// unsynchronized
		return exec.submit(new Callable<Integer>() {
			public Integer call() { return new Integer(fibonacci(n)); }
		}); 
	}
	
	public static Integer fibonacci(int n) {
		try {
			if (n<2) return 1;
			else {
				int left = fibonacci(n-1);
				Thread.sleep(0);
				int right = fibonacci(n-2);
				return left + right;
			}
		} catch (InterruptedException _) { 
			return null;
		}
	}
}

