package ru.job4j.tracker;

public class StartUI {

    private void showMenu(Tracker tracker) {
        System.out.println("Menu:");
        for (int i = 0; i < tracker.getActionsCount(); i++) {
            System.out.println(String.format("%s. %s", i, tracker.getActionByIndex(i).getName()));
        }
    }

    public void init(Input input, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu(tracker);
            Action action = tracker.getActionByIndex(
                    input.askInt("Select: ", tracker.getActionsCount())
            );
            run = action.execute(input, tracker);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Tracker tracker = new Tracker()
                .addAction(new ActionNewItem())
                .addAction(new ActionShowAll())
                .addAction(new ActionRenameItem())
                .addAction(new ActionDeleteItem())
                .addAction(new ActionFindItemById())
                .addAction(new ActionFindItemByName())
                .addAction(new ActionExitProgram());
        new StartUI().init(new InputValidate(new InputConsole()), tracker);
    }

}
