package tracker.actions;

import tracker.AbstractTracker;
import tracker.input.IInput;
import tracker.items.Item;

import java.io.OutputStream;
import java.util.List;

public class FindItemByName extends AbstractAction {

    public FindItemByName() {
        super("Find item by name");
    }

    @Override
    public boolean action(IInput input, AbstractTracker tracker) {
        try {
            OutputStream outputStream = tracker.getOutput();
            String name = input.askString("Enter item name: ");
            List<Item> items = tracker.findByName(name);
            if (items.size() > 0) {
                for (Item item : items) {
                    outputStream.write((item.toString() + "\n").getBytes());
                }
            } else {
                outputStream.write("Item not found\n".getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}