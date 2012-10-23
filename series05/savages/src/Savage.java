public class Savage extends Thread {
    private Pot pot;

    public Savage(String name, Pot pot) {
        super(name);
        this.pot = pot;
    }

    @Override
    public void run() {
        //noinspection InfiniteLoopStatement
        while(true) {
            pot.getServing();
        }
    }
}
