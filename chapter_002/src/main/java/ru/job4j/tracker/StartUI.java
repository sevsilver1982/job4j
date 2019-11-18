package ru.job4j.tracker;

public class StartUI {

    public void init(Input input, Tracker tracker, Action[] actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ", actions.length);
            Action action = actions[select];
            run = action.execute(input, tracker);
            System.out.println();
        }
    }

    private void showMenu(Action[] actions) {
        System.out.println("Menu:");
        for (int i = 0; i < actions.length; i++) {
            System.out.println(i + ". " + actions[i].getName());
        }
    }

    public static void main(String[] args) {
        Input consoleInput = new InputValidate();
        Tracker tracker = new Tracker();
        Action[] actionsTracker = new ActionTracker[] {
            new ActionNewItem(),
            new ActionShowAll(),
            new ActionEditItem(),
            new ActionDeleteItem(),
            new ActionFindItemById(),
            new ActionFindItemByName(),
            new ActionExitProgram()
        };
        new StartUI().init(consoleInput, tracker, actionsTracker);
    }

}
