package tracker.actions;

import tracker.AbstractTracker;
import tracker.input.IInput;
import tracker.items.Item;

import java.util.List;

public class ShowAll extends AbstractAction {

    public ShowAll() {
        super("Show all items");
    }

    @Override
    public boolean action(IInput input, AbstractTracker tracker) {
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