package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Tracker;

public class ExitProgram extends ActionTracker {

    public ExitProgram() {
        super("Exit program");
    }

    @Override
    public boolean action(Input input, Tracker tracker) {
        return false;
    }

}