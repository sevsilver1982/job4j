package tracker.actions;

import tracker.AbstractTracker;
import tracker.input.IInput;
import tracker.items.Item;

import java.util.List;

public class FindItemByName extends AbstractAction {

    public FindItemByName() {
        super("Find item by name");
    }

    @Override
    public boolean action(IInput input, AbstractTracker tracker) {
        String name = input.askString("Enter item name: ");
        List<Item> items = tracker.findByName(name);
        if (items.size() > 0) {
            for (Item item : items) {
                System.out.println(item.toString());
            }
        } else {
            System.out.println("Item not found");
        }
        return true;
    }

}