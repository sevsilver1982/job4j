package tracker.actions;

import tracker.ITracker;
import tracker.input.Input;

public class ExitProgram extends ActionTracker {

    public ExitProgram() {
        super("Exit program");
    }

    @Override
    public boolean action(Input input, ITracker tracker) {
        return false;
    }

}