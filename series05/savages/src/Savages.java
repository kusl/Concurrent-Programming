public class Savages {
    public static void main(String[] args) {
        // Create a pot, a savage and a cook. Then let the feast begin!
        Pot pot = new GuardedPot();
        Savage savage = new Savage("Savage", pot);
        Cook cook = new Cook("Cook", pot);
        savage.start();
        cook.start();
    }
}
