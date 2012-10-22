public class RussianRoulette {
    public static void main(String[] args) {
        // Creates a gun with 1 bullet and two players
        Gun gun = new GuardedGunWithDeadlock(1);
        Player michael = new Player("Michael", gun);
        Player charlton = new Player("Charlton", gun);
        // Starts the game by starting both players
        michael.start();
        charlton.start();
    }
}
