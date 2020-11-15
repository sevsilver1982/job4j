import actions.AbstractTracker;
import items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Tracker extends AbstractTracker {
    private List<Item> items = new ArrayList<>();

    public Item add(Item item) {
        items.add(item);
        return item;
    }

    public boolean replace(UUID id, Item newItem) {
        int index = indexById(id);
        if (index >= 0) {
            items.set(index, newItem);
            return true;
        }
        return false;
    }

    public boolean delete(UUID id) {
        return items.remove(indexById(id)) != null;
    };

    public int indexById(UUID id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public Item findById(UUID id) {
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