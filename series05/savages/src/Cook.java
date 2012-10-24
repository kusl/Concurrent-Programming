// A cook is modeled as a thread
public class Cook extends Thread {
    private Pot pot;

    public Cook(String name, Pot pot) {
        super(name);
        this.pot = pot;
    }

    @Override
    public void run() {
        // We create an infinite loop, where we always try to fill the pot. We don't care, under which conditions,
        // we're allowed to fill the pot. The guard will watch out for that.
        while(true) {
            pot.fill(5);
        }
    }
}
