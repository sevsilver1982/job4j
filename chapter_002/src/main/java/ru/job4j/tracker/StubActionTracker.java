package ru.job4j.tracker;

public class StubActionTracker extends ActionTracker implements Action  {
    private boolean call = true;

    public StubActionTracker() {
        super("Stub action", false);
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        this.call = true;
        return false;
    }

    public boolean isCall() {
        return this.call;
    }
}