package tracker;

import tracker.actions.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tracker {
    private List<Action> actions = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private final Random rm = new Random();

    public List<Action> findAllActions() {
        return this.actions;
    }

    private String generateId() {
        return String.valueOf(Math.abs(rm.nextLong() + System.currentTimeMillis()));
    }

    public boolean addAction(Action action) {
        action.setId(this.actions.size() + 1);
        return this.actions.add(action);
    }

    public boolean add(Item item) {
        item.setId(generateId());
        return this.items.add(item);
    }

    public boolean rename(String id, String newName) {
        boolean result = false;
        Item item = this.findById(id);
        if (item.getId() != null && item.getName() != null) {
            item.setName(newName);
            result = true;
        }
        return result;
    }

    public boolean delete(Item item) {
        return this.items.remove(item);
    };

    public Item findById(String id) {
        Item result = new Item();
        for (Item item : this.items) {
            if (item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    public List<Item> findByName(String name) {
        List<Item> items = new ArrayList<>();
        for (Item item : this.items) {
            if (item.getName().equals(name)) {
                items.add(item);
            }
        }
        return items;
    }

    public List<Item> findAllItems() {
        return this.items;
    }

}