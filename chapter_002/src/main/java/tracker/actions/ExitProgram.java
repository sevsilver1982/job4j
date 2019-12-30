package tracker.actions;

import tracker.input.Input;
import tracker.Tracker;

public class ExitProgram extends ActionTracker {

    public ExitProgram() {
        super("Exit program");
    }

    @Override
    public boolean action(Input input, Tracker tracker) {
        return false;
    }

}