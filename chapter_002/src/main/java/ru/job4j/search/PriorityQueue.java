package ru.job4j.search;

import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позицию определять по полю приоритет.
     * Для вставок использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
        int i = (Math.min(task.getPriority(), tasks.size())) - 1;
        i = Math.max(i, 0);
        this.tasks.add(
                i,
                task);
    }

    public Task take() {
        return this.tasks.poll();
    }
}
