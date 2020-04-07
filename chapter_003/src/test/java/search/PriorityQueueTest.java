package search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(
                7,
                queue.size()
        );
        queue.take();
        queue.take();
        queue.take();
        var task = queue.take();
        assertEquals(
                3,
                queue.size()
        );
        assertEquals(
                "3",
                task.getDesc()
        );
    }

}