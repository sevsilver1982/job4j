package actions;

import items.Item;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class AbstractTracker {
    private List<IAction> actions = new ArrayList<>();
    private OutputStream output;

    public abstract Item add(Item item);

    public abstract boolean replace(UUID id, Item item);

    public abstract boolean delete(UUID id);

    public abstract List<Item> findAll();

    public abstract List<Item> findByName(String name);

    public abstract Item findById(UUID id);

    public OutputStream getOutput() {
        return output;
    }

    public void setOutput(OutputStream output) {
        this.output = output;
    }

    public void setActionList(List<IAction> actions) {
        actions.forEach(action -> {
            action.setId(this.actions.size() + 1);
            this.actions.add(action);
        });
    }

    public List<IAction> getActionList() {
        return actions;
    }

}