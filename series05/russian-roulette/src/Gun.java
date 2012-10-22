public interface Gun {
    // Implemented interface to be extensible to different policies
    public void pickup(Player player);
    public void putdown();
    public void trigger();
}