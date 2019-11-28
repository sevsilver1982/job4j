package ru.job4j.tracker;

public interface Action {
    String getName();
    boolean execute(Input input, Tracker tracker);
}