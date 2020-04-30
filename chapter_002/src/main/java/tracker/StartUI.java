package tracker;

import tracker.actions.*;
import tracker.input.IInput;
import tracker.input.InputConsole;
import tracker.input.InputValidate;

import java.util.List;
import java.util.function.Consumer;

public class StartUI {
    private final IInput input;
    private final AbstractTracker tracker;
    private final Consumer<String> output;

    public StartUI(IInput input, AbstractTracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    private void showMenu(AbstractTracker tracker, Consumer<String> output) {
        System.out.println("Menu:");
        tracker.getActionList()
                .stream()
                .map(action -> String.format("%s. %s", action.getId(), action.getName()))
                .forEach(output::accept);
    }

    public void init() {
        boolean run = true;
        do {
            this.showMenu(tracker, output);
            List<IAction> actions = tracker.getActionList();
            int select = input.askInt("Select: ", actions.size() + 1);
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
        tracker.setActionList(
                List.of(
                        new NewItem(),
                        new ShowAll(),
                        new ReplaceItem(),
                        new DeleteItem(),
                        new FindItemById(),
                        new FindItemByName(),
                        new ExitProgram()
                )
        );
        new StartUI(
                new InputValidate(new InputConsole()),
                tracker,
                System.out::println
        ).init();
    }

}