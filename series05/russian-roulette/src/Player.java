public class Player extends Thread {
    private Gun gun;
    private boolean alive;

    public Player(String name, Gun gun) {
        super(name);
        this.gun = gun;
        alive = true;
        System.out.println(getName() + " entered the game. Poor bastard.");
    }

    @Override
    public void run() {
        // While a player is alive, he tries to pickup the gun, trigger it and (if still alive) puts it down again.
        while (alive) {
            gun.pickup(this);
            gun.trigger();
            if(alive) {
                gun.putdown();
            }
            // Potential deadlock: if killed, the gun is never put down again
        }
    }

    public void kill() {
        System.out.println(getName() + " shot himself!");
        alive = false;
    }
}