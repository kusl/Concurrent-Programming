public class GuardedPot implements Pot {
    private int servings;

    @Override
    public synchronized void fill(int m) {
        System.out.println("Someone tries to fill the pot (Servings: "+servings+")");
        while(servings > 0) {
            try {
                wait();
            } catch (InterruptedException ignored) {}
        }
        servings += m;
        notifyAll();
        assert invariant();
    }

    @Override
    public synchronized void getServing() {
        System.out.println("Someone tries to get a serving (Servings: "+servings+")");
        while(servings <= 0) {
            try {
                wait();
            } catch (InterruptedException ignored) {}
        }
        servings -= 1;
        notifyAll();
        assert invariant();
    }

    private boolean invariant() {
        if(servings < 0) {
            System.out.println("Invariant broken!");
            return false;
        } else {
            return true;
        }
    }
}
