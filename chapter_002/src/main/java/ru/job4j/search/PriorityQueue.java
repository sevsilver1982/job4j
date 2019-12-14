package ru.job4j.search;

import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    public int size() {
        return this.tasks.size();
    }

    public void put(Task task) {
        for (int i = 0; i <= this.tasks.size(); i++) {
            if (this.tasks.size() == i || this.tasks.get(i).getPriority() >= task.getPriority()) {
                this.tasks.add(i, task);
                break;
            }
        }
    }

    public Task take() {
        return this.tasks.poll();
    }
}
