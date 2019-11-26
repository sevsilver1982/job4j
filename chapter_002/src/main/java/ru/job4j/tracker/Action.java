package ru.job4j.tracker;

public interface Action {
    int getKey();
    void setKey(int key);
    String getName();
    void setName(String name);
    boolean execute(Input input, Tracker tracker);
}