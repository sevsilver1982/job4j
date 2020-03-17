package tracker.actions;

import tracker.ITracker;
import tracker.Item;
import tracker.input.Input;

import java.util.List;

public class FindItemByName extends ActionTracker {

    public FindItemByName() {
        super("Find item by name");
    }

    @Override
    public boolean action(Input input, ITracker tracker) {
        String name = input.askString("Enter item name: ");
        List<Item> items = tracker.findByName(name);
        if (items.size() > 0) {
            for (Item item : items) {
                System.out.println(String.format("%s found by name", item.toString()));
            }
        } else {
            System.out.println("Item not found");
        }
        return true;
    }

}