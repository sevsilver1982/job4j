package tracker.actions;

import tracker.AbstractTracker;
import tracker.input.IInput;
import tracker.items.Item;

public class NewItem extends AbstractAction {

    public NewItem() {
        super("Add new item");
    }

    @Override
    public boolean action(IInput input, AbstractTracker tracker) {
        Item item = new Item(input.askString("Enter name: "));
        tracker.add(item);
        System.out.println(item.toString());
        return true;
    }

}