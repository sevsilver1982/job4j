package tracker;

import tracker.actions.IAction;
import tracker.items.Item;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTracker {
    private List<IAction> actions = new ArrayList<>();

    public abstract Item add(Item item);
    public abstract boolean replace(String id, Item item);
    public abstract boolean delete(String id);
    public abstract List<Item> findAll();
    public abstract List<Item> findByName(String name);
    public abstract Item findById(String id);
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