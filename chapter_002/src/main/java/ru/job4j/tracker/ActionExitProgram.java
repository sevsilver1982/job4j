package ru.job4j.tracker;

public class ActionExitProgram extends ActionTracker {

    public ActionExitProgram() {
        super("Exit program");
    }

    @Override
    public boolean action(Input input, Tracker tracker) {
        return false;
    }

}