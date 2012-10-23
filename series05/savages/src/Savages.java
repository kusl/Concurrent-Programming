public class Savages {
    public static void main(String[] args) {
        Pot pot = new GuardedPot();
        Savage savage = new Savage("Savage", pot);
        Cook cook = new Cook("Cook", pot);
        savage.start();
        cook.start();
    }
}
