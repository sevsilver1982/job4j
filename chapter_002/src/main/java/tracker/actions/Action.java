package tracker.actions;

import tracker.input.Input;
import tracker.Tracker;

public interface Action {
    String getName();
    int getId();
    void setId(int id);
    boolean execute(Input input, Tracker tracker);
}