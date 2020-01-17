package tracker.actions;

import tracker.Tracker;
import tracker.input.Input;

public abstract class ActionTracker implements Action {
    private String name;
    private int id;

    public ActionTracker(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public abstract boolean action(Input input, Tracker tracker);

    public boolean execute(Input input, Tracker tracker) {
        System.out.println(String.format("==== %s ====", getName()));
        return action(input, tracker);
    }

}