package tracker;

import tracker.actions.*;
import tracker.input.Input;
import tracker.input.InputConsole;
import tracker.input.InputValidate;

import java.util.List;
import java.util.function.Consumer;

public class StartUI {
    private final Input input;
    private final Tracker tracker;
    private final Consumer<String> output;

    private void showMenu(Tracker tracker, Consumer<String> output) {
        System.out.println("Menu:");
        tracker.findAllActions()
                .stream()
                .map(action -> String.format("%s. %s", action.getId(), action.getName()))
                .forEach(output::accept);
    }

    public StartUI(Input input, Tracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    public void init() {
        boolean run = true;
        while (run) {
            this.showMenu(this.tracker, this.output);
            List<Action> actions = this.tracker.findAllActions();
            int select = this.input.askInt("Select: ", actions.size() + 1);
            for (Action action : actions) {
                if (action.getId() == select) {
                    run = action.execute(this.input, this.tracker);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        tracker.addAction(new NewItem());
        tracker.addAction(new ShowAll());
        tracker.addAction(new ReplaceItem());
        tracker.addAction(new DeleteItem());
        tracker.addAction(new FindItemById());
        tracker.addAction(new FindItemByName());
        tracker.addAction(new ExitProgram());
        new StartUI(new InputValidate(
                new InputConsole()),
                tracker,
                System.out::println
        ).init();
    }

}
