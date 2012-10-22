import java.util.Random;

// A gun has a number of chambers, each chamber can have a bullet or not. It has also a pointer to the active chamber.
public class GuardedGunWithDeadlock implements Gun {
    protected final int MAX_CHAMBER = 2;
    protected boolean[] chambers;
    protected int active;

    protected Player owner;

    public GuardedGunWithDeadlock(int bullets) {
        chambers = new boolean[MAX_CHAMBER];

        for(int i = 0; i < chambers.length; i++) {
            chambers[i] = false;
        }

        while(bullets > 0) {
            int slot = new Random().nextInt(MAX_CHAMBER);
            if(!chambers[slot]) {
                chambers[slot] = true;
                bullets -= 1;
            }
        }

        active = new Random().nextInt(MAX_CHAMBER);
    }

    @Override
    public synchronized void pickup(Player player) {
        System.out.println(player.getName() + " wants to pickup the gun.");
        // Guarded method => Only one player can have the gun. If somebody already has the gun, the thread must wait.
        while(owner != null) {
            try {
                // No need to check for an invariant, as no action has been performed so far.
                wait();
            } catch (InterruptedException ignored) {}
        }
        System.out.println(player.getName() + " now has the gun. Oh dear!");
        this.owner = player;
    }

    @Override
    public synchronized void putdown() {
        System.out.println(owner.getName() + " puts down the gun.");
        this.owner = null;
        // As the state of the gun has changed, we call notifyAll() to wake up potential threads.
        // Note: In this example, notify would be sufficient!
        notifyAll();
    }

    // When triggered, the active chamber advances one and then a bullet is fired, if there is one in the chamber.
    @Override
    public void trigger() {
        // Precondition, when the gun is triggered, it must have a owner.
        assert owner != null;
        advance();
        if(chambers[active]) {
            System.out.println("BANG!!!");
            chambers[active] = false;
            owner.kill();
            // DEADLOCK => no chance to get the owner set to null again
        } else {
            System.out.println("KLICK!!!");
        }
    }

    protected void advance() {
        if(active < MAX_CHAMBER-1) {
            active += 1;
        } else {
            active = 0;
        }
    }
}
