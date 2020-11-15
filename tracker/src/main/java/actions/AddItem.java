package actions;

import input.IInput;
import items.Item;

import java.io.OutputStream;

public class AddItem extends AbstractAction {

    public AddItem() {
        super("Add new item");
    }

    @Override
    public boolean action(IInput input, AbstractTracker tracker) {
        try {
            OutputStream outputStream = tracker.getOutput();
            Item item = new Item(input.askString("Enter name: "));
            tracker.add(item);
            outputStream.write((item.toString() + "\n").getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}