package ru.job4j.tracker;

public class ActionTracker implements Action {
    private String name;
    private boolean processNext;

    public ActionTracker(String name, boolean processNext) {
        this.name = name;
        this.processNext = processNext;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        return this.processNext;
    }

}