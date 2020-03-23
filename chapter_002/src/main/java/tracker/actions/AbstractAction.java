package tracker.actions;

import tracker.AbstractTracker;
import tracker.input.IInput;

public abstract class AbstractAction implements IAction {
    private final String name;
    private int id;

    public AbstractAction(String name) {
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

    public abstract boolean action(IInput input, AbstractTracker tracker);

    public boolean execute(IInput input, AbstractTracker tracker) {
        System.out.println(String.format("==== %s ====", getName()));
        return action(input, tracker);
    }

}