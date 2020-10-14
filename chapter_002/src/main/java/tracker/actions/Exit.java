package tracker.actions;

import tracker.AbstractTracker;
import tracker.input.IInput;

public class Exit extends AbstractAction {

    public Exit() {
        super("Exit program");
    }

    @Override
    public boolean action(IInput input, AbstractTracker tracker) {
        return false;
    }

}