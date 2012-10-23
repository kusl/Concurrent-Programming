public class Cook extends Thread {
    private Pot pot;

    public Cook(String name, Pot pot) {
        super(name);
        this.pot = pot;
    }

    @Override
    public void run() {
        //noinspection InfiniteLoopStatement
        while(true) {
            pot.fill(5);
        }
    }
}
