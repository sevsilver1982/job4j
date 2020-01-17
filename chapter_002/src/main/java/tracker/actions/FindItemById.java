package tracker.actions;

import tracker.Item;
import tracker.Tracker;
import tracker.input.Input;

public class FindItemById extends ActionTracker {

    public FindItemById() {
        super("Find item by id");
    }

    @Override
    public boolean action(Input input, Tracker tracker) {
        String id = input.askString("Enter item id: ");
        Item item = tracker.findById(id);
        if (item.getId() != null) {
            System.out.println(String.format("%s found by id", item.toString()));
        } else {
            System.out.println("Item not found");
        }
        return true;
    }

}