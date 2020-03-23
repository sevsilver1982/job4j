package tracker.actions;

import tracker.AbstractTracker;
import tracker.input.IInput;

public interface IAction {
    String getName();
    int getId();
    void setId(int id);
    boolean execute(IInput input, AbstractTracker tracker);
}