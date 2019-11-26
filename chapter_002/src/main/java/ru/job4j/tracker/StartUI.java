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
            System.out.println(String.format("%s. %s", i, actions[i].getName()));
        }
    }

    public static void main(String[] args) {
        new StartUI().init(
                new InputValidate(new InputConsole()),
                new Tracker(),
                new ActionTracker[] {
                        new ActionNewItem(),
                        new ActionShowAll(),
                        new ActionEditItem(),
                        new ActionDeleteItem(),
                        new ActionFindItemById(),
                        new ActionFindItemByName(),
                        new ActionExitProgram()
                }
        );
    }

}
