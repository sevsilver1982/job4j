package ru.job4j.tracker;

public interface Action {
    String getName();
    void setName(String name);
    boolean execute(Input input, Tracker tracker);
}