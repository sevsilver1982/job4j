package actions;

import input.IInput;
import items.Item;

import java.io.OutputStream;
import java.util.List;

public class ShowAll extends AbstractAction {

    public ShowAll() {
        super("Show all items");
    }

    @Override
    public boolean action(IInput input, AbstractTracker tracker) {
        try {
            OutputStream outputStream = tracker.getOutput();
            List<Item> items = tracker.findAll();
            if (items.size() > 0) {
                for (Item item : items) {
                    outputStream.write((item.toString() + "\n").getBytes());
                }
            } else {
                outputStream.write("Items not found\n".getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}