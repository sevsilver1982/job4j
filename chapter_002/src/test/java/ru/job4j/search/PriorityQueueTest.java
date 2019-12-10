package ru.job4j.search;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PriorityQueueTest {
    @Test
    public void whenHigherPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("5", 5));
        queue.put(new Task("1", 1));
        queue.put(new Task("3", 3));
        queue.put(new Task("6", 6));
        queue.put(new Task("2", 2));
        queue.put(new Task("4", 4));
        queue.put(new Task("1", 1));
        queue.take();
        queue.take();
        queue.take();
        assertThat(queue.take().getDesc(), is("3"));
    }
}
