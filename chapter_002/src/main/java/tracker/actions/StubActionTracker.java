package tracker.actions;

import tracker.ITracker;
import tracker.input.Input;

public class StubActionTracker extends ActionTracker implements Action {
    private boolean call = true;

    public StubActionTracker() {
        super("Stub action");
    }

    @Override
    public boolean action(Input input, ITracker tracker) {
        this.call = true;
        return false;
    }

    public boolean isCall() {
        return this.call;
    }

}