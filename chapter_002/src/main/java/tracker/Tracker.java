package tracker;

import tracker.actions.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Tracker implements ITracker {
    private List<Action> actions = new ArrayList<>();
    private List<Item> items = new ArrayList<>();

    public List<Action> actionList() {
        return actions;
    }

    public boolean addAction(Action action) {
        action.setId(actions.size() + 1);
        return actions.add(action);
    }

    public Item add(Item item) {
        items.add(item);
        return item;
    }

    public boolean replace(String id, Item newItem) {
        int index = indexById(id);
        if (index >= 0) {
            items.set(index, newItem);
            return true;
        }
        return false;
    }

    public boolean delete(String id) {
        return items.remove(indexById(id)) != null;
    };

    public int indexById(String id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public Item findById(String id) {
        return items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElse(new Item());
    }

    public List<Item> findByName(String name) {
        return items.stream()
                .filter(item -> item.getName().equals(name))
                .collect(Collectors.toList());
    }

    public List<Item> findAll() {
        return items;
    }

}