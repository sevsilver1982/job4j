package tracker.actions;

import tracker.Item;
import tracker.Tracker;
import tracker.input.Input;

import java.util.List;

public class ShowAll extends ActionTracker {

    public ShowAll() {
        super("Show all items");
    }

    @Override
    public boolean action(Input input, Tracker tracker) {
        List<Item> items = tracker.findAll();
        if (items.size() > 0) {
            for (Item item : items) {
                System.out.println(item.toString());
            }
        } else {
            System.out.println("Items not found");
        }
        return true;
    }

}