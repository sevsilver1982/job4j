package tracker.actions;

import tracker.AbstractTracker;
import tracker.input.IInput;

import java.io.OutputStream;

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
        try {
            OutputStream outputStream = tracker.getOutput();
            if (outputStream == null) {
                throw new Exception("Output stream not assigned");
            }
            outputStream.write(
                    String.format("==== %s ====\n", getName()).getBytes()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return action(input, tracker);
    }

}