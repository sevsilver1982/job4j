package ru.job4j.tracker.actions;

import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.input.Input;

public class StubActionTracker extends ActionTracker implements Action {
    private boolean call = true;

    public StubActionTracker() {
        super("Stub action");
    }

    @Override
    public boolean action(Input input, Tracker tracker) {
        this.call = true;
        return false;
    }

    public boolean isCall() {
        return this.call;
    }

}