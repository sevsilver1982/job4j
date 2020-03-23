package tracker.actions;

import tracker.AbstractTracker;
import tracker.input.IInput;
import tracker.items.Item;

public class ReplaceItem extends AbstractAction {

    public ReplaceItem() {
        super("Replace item");
    }

    @Override
    public boolean action(IInput input, AbstractTracker tracker) {
        Item item = tracker.findById(input.askString("Enter item id: "));
        if (item.getId() != null && item.getName() != null) {
            tracker.replace(
                    item.getId(),
                    new Item(input.askString("Enter new item name: "))
            );
            System.out.println(item.toString());
        } else {
            System.out.println("Item not found");
        }
        return true;
    }

}