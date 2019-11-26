package ru.job4j.tracker;

public class ActionExitProgram extends ActionTracker {
    public ActionExitProgram() {
        super("Exit program", false);
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println(String.format("=== %s ====", super.getName()));
        return super.execute(input, tracker);
    }
}
