package tracker.actions;

import tracker.Tracker;
import tracker.input.Input;

public class ExitProgram extends ActionTracker {

    public ExitProgram() {
        super("Exit program");
    }

    @Override
    public boolean action(Input input, Tracker tracker) {
        return false;
    }

}