package tracker.actions;

import tracker.AbstractTracker;
import tracker.input.IInput;
import tracker.items.Item;

public class FindItemById extends AbstractAction {

    public FindItemById() {
        super("Find item by id");
    }

    @Override
    public boolean action(IInput input, AbstractTracker tracker) {
        String id = input.askString("Enter item id: ");
        Item item = tracker.findById(id);
        if (item.getId() != null) {
            System.out.println(item.toString());
        } else {
            System.out.println("Item not found");
        }
        return true;
    }

}