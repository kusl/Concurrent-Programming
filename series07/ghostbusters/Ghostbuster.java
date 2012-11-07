package ghostbusters;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Ghostbuster extends Thread {
	List<Gatekeeper> friends = Arrays.asList(new Gatekeeper(), new Gatekeeper(), new Gatekeeper(), new Gatekeeper());
	int killed = 0;
	Random rand = new Random();
	int maxGhostsICanHandle;
	List<Future<Integer>> answers;
	int tick;
	int currentHypothesis=0;
	ExecutorService mobilePhone = Executors.newFixedThreadPool(5);
	boolean currentlyAsking = false;
	boolean closed = true;
	
	
	public Ghostbuster(int n) {
		maxGhostsICanHandle = n;
	}
	
	public static void main(String[] args) {
		
		int handleable =  (args.length >= 1)? Integer.parseInt(args[0]) : 10;
		new Ghostbuster(handleable).start();
	}
	
	public void run() {
		goToTheDoorsAndLetThemInSlowlyIllKillThem();
		while (true) {
			askHowManyAreInTheHouse();
			killGhosts();
			int ghosts = howManyAreInTheHouse();
			if (ghosts > maxGhostsICanHandle / 2 && !closed) {
				closeTheGoddamnDoors();
			}
			if (ghosts < maxGhostsICanHandle / 2 && closed) {
				okLetThemIn();
			}
			askMeHowTheHeckImDoingTwoSecondsFromNow();
		}
	}

	private void askMeHowTheHeckImDoingTwoSecondsFromNow() {
		if (currentlyAsking) return;
		mobilePhone.execute(new Runnable(){
			@Override
			public void run() {
				synchronized(Ghostbuster.this) {
					currentlyAsking = true;
				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) { }
				System.out.println("We currently have "+currentHypothesis+" ghosts");
				synchronized(Ghostbuster.this) {
					currentlyAsking = false;
				}
			}
		});		
	}

	private void goToTheDoorsAndLetThemInSlowlyIllKillThem() {
		System.out.println("Go to the doors and let them in slowly! I'll kill them for good! Go GO GO!");
		closed = false;
		for (Gatekeeper g: friends) 
			g.start();
	}
	
	private void justSleepDammit(long milli) {
		try {
			Thread.sleep(10);
		} catch (InterruptedException _) {	}
	}

	private void killGhosts() {
		justSleepDammit(10);
		if (currentHypothesis > 0 && rand.nextInt(10)<4)
			killed++;
	}

	private void askHowManyAreInTheHouse() {
//		System.out.println("Ok guys, how many have you got?");
		answers = new LinkedList<Future<Integer>>();
		for (Gatekeeper g: friends) {
			Future<Integer> answer = mobilePhone.submit(g.askAdmitted());
			answers.add(answer);
		}
	}

	private void okLetThemIn() {
		System.out.println("Ok, I'm better now, let them come!");
		for (Gatekeeper g: friends) {
			mobilePhone.submit(g.askGo());
		}
		closed = false;
	}

	private void closeTheGoddamnDoors() {
		System.out.println(""+currentHypothesis+"? that's too many, close the god-damn doors!");
		for (Gatekeeper g: friends) {
			mobilePhone.submit(g.askPause());
		}
		closed = true;
	}

	private int howManyAreInTheHouse() {
		int sum = 0;
		for (Future<Integer> f: answers) {
			try {
				sum += f.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		synchronized(this) {
//			System.out.println("So we have "+sum+" ghosts admitted and "+killed+" ghosts killed");
			this.currentHypothesis = sum - killed;
		}
		return currentHypothesis;
	}
}
