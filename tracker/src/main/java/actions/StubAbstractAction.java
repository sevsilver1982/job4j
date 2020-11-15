package actions;

import input.IInput;

public class StubAbstractAction extends AbstractAction implements IAction {
    private boolean call = true;

    public StubAbstractAction() {
        super("Stub action");
    }

    @Override
    public boolean action(IInput input, AbstractTracker tracker) {
        this.call = true;
        return false;
    }

    public boolean isCall() {
        return this.call;
    }

}