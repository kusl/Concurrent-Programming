// Savage is modeled as a thread.
public class Savage extends Thread {
    private Pot pot;

    public Savage(String name, Pot pot) {
        super(name);
        this.pot = pot;
    }

    @Override
    public void run() {
        //we create an infinite loop and then want to get a serving. No checks necessary, the pot will do that for us.
        while(true) {
            pot.getServing();
        }
    }
}
