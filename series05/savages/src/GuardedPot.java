//Implementation for the Pot with guarded methods
public class GuardedPot implements Pot {
    private int servings;

    @Override
    public synchronized void fill(int m) {
        System.out.println("Someone tries to fill the pot (Servings: "+servings+")");
        // Guard checks, if there are still servings in the pot. If so, the calling thread has to wait.
        while(servings > 0) {
            try {
                wait();
            } catch (InterruptedException ignored) {}
        }
        servings += m;
        // After the state change, we notify other threads and check the invariant.
        notifyAll();
        assert invariant();
    }

    @Override
    public synchronized void getServing() {
        System.out.println("Someone tries to get a serving (Servings: "+servings+")");
        // This method is guarded too. A client can only get a serving, if there are more than 0 servings there.
        while(servings <= 0) {
            try {
                wait();
            } catch (InterruptedException ignored) {}
        }
        servings -= 1;
        if(servings <= 0) {
            // Waiting threads can only be a cook. This one needs an empty pot. Therefore we can check that before
            // calling notify. We prevent some unnecessary notifyAll() calls. In case of multiple savages, notify()
            // could lead to deadlocks.
            notifyAll();
        }
        assert invariant();
    }

    private boolean invariant() {
        if(servings < 0) {
            // In case the invariant is broken, we want to see that in the console.
            System.out.println("Invariant broken!");
            return false;
        } else {
            return true;
        }
    }
}