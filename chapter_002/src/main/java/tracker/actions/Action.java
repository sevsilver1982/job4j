package tracker.actions;

import tracker.Tracker;
import tracker.input.Input;

public interface Action {
    String getName();
    int getId();
    void setId(int id);
    boolean execute(Input input, Tracker tracker);
}