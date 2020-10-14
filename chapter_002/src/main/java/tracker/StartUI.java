package tracker;

import tracker.actions.*;
import tracker.input.IInput;
import tracker.input.InputConsole;
import tracker.input.InputValidate;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class StartUI {
    private final IInput input;
    private final AbstractTracker tracker;

    public StartUI(IInput input, AbstractTracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    private void showMenu(AbstractTracker tracker) {
        try {
            OutputStream outputStream = tracker.getOutput();
            if (outputStream != null) {
                outputStream.write("Menu:\n".getBytes());
            }
            for (IAction action : tracker.getActionList()) {
                String x = String.format("%s. %s", action.getId(), action.getName());
                if (outputStream != null) {
                    outputStream.write((x + "\n").getBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() {
        boolean run = true;
        do {
            this.showMenu(tracker);
            List<IAction> actions = tracker.getActionList();
            int select = input.askInt("Select: ", actions.size());
            for (IAction action : actions) {
                if (action.getId() == select) {
                    run = action.execute(input, tracker);
                    break;
                }
            }
            System.out.println();
        } while (run);
    }

    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        tracker.setOutput(System.out);
        tracker.setActionList(
                List.of(
                        new AddItem(),
                        new ShowAll(),
                        new ReplaceItem(),
                        new DeleteItem(),
                        new FindItemById(),
                        new FindItemByName(),
                        new Exit()
                )
        );
        new StartUI(
                new InputValidate(new InputConsole()),
                tracker
        ).init();
    }

}