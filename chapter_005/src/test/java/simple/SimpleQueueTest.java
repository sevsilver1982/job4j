package simple;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleQueueTest {

    @Test
    public void all() {
        assertThrows(
                NoSuchElementException.class,
                () -> {
                    SimpleQueue<Integer> queue = new SimpleQueue<>();
                    queue.push(1);
                    assertEquals(
                            1,
                            queue.getSize()
                    );
                    queue.push(2);
                    assertEquals(
                            2,
                            queue.getSize()
                    );
                    queue.push(3);
                    assertEquals(
                            3,
                            queue.getSize()
                    );
                    assertEquals(
                            1,
                            queue.poll()
                    );
                    assertEquals(
                            2,
                            queue.getSize()
                    );
                    assertEquals(
                            2,
                            queue.poll()
                    );
                    assertEquals(
                            1,
                            queue.getSize()
                    );
                    assertEquals(
                            3,
                            queue.poll()
                    );
                    assertEquals(
                            0,
                            queue.getSize()
                    );
                    queue.poll();
                });
    }

}