package search;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PriorityQueueTest {

    @Test
    public void whenHigherPriority() {
        var queue = new PriorityQueue();
        queue.put(new Task("5", 5));
        queue.put(new Task("1", 1));
        queue.put(new Task("3", 3));
        queue.put(new Task("6", 6));
        queue.put(new Task("2", 2));
        queue.put(new Task("4", 4));
        queue.put(new Task("1", 1));
        assertThat(queue.size(), is(7));
        queue.take();
        queue.take();
        queue.take();
        var task = queue.take();
        assertThat(queue.size(), is(3));
        assertThat(task.getDesc(), is("3"));
    }

}