package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Tracker;

public interface Action {
    String getName();
    int getId();
    void setId(int id);
    boolean execute(Input input, Tracker tracker);
}