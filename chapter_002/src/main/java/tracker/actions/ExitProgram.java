package tracker.actions;

import tracker.AbstractTracker;
import tracker.input.IInput;

public class ExitProgram extends AbstractAction {

    public ExitProgram() {
        super("Exit program");
    }

    @Override
    public boolean action(IInput input, AbstractTracker tracker) {
        return false;
    }

}