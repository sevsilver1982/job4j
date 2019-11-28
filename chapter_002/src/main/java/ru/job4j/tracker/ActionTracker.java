package ru.job4j.tracker;

public abstract class ActionTracker implements Action {
    private String name;

    public ActionTracker(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public abstract boolean action(Input input, Tracker tracker);

    public boolean execute(Input input, Tracker tracker) {
        System.out.println(String.format("==== %s ====", getName()));
        return action(input, tracker);
    }

}