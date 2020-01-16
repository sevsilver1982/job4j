package tracker;

import tracker.actions.*;
import tracker.input.Input;
import tracker.input.InputConsole;
import tracker.input.InputValidate;

import java.util.List;

public class StartUI {

    private void showMenu(Tracker tracker) {
        System.out.println("Menu:");
        List<Action> actions = tracker.findAllActions();
        for (Action action : actions) {
            System.out.println(String.format("%s. %s", action.getId(), action.getName()));
        }
    }

    public void init(Input input, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu(tracker);
            List<Action> actions = tracker.findAllActions();
            int select = input.askInt("Select: ", actions.size() + 1);
            for (Action action : actions) {
                if (action.getId() == select) {
                    run = action.execute(input, tracker);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        tracker.addAction(new NewItem());
        tracker.addAction(new ShowAll());
        tracker.addAction(new RenameItem());
        tracker.addAction(new DeleteItem());
        tracker.addAction(new FindItemById());
        tracker.addAction(new FindItemByName());
        tracker.addAction(new ExitProgram());
        new StartUI().init(new InputValidate(new InputConsole()), tracker);
    }

}
