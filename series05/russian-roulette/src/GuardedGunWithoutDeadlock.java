public class GuardedGunWithoutDeadlock extends GuardedGunWithDeadlock {
    public GuardedGunWithoutDeadlock(int bullets) {
        super(bullets);
    }

    // Due to the call of notify, we must call the method synchronized
    @Override
    public synchronized void trigger() {
        assert owner != null;
        advance();
        if(chambers[active]) {
            System.out.println("BANG!!!");
            chambers[active] = false;
            owner.kill();
            // Prevent the deadlock by setting the owner back to null and then call notifyAll()
            owner = null;
            notifyAll();
        } else {
            System.out.println("KLICK!!!");
        }
    }
}
