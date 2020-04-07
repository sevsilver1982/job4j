package simple;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleStackTest {

    @Test
    public void all() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        assertEquals(
                1,
                stack.getSize()
        );
        stack.push(2);
        assertEquals(
                2,
                stack.getSize()
        );
        stack.push(3);
        assertEquals(
                3,
                stack.getSize()
        );
        assertEquals(
                3,
                stack.poll()
        );
        assertEquals(
                2,
                stack.getSize()
        );
        assertEquals(
                2,
                stack.poll()
        );
        assertEquals(
                1,
                stack.getSize()
        );
        assertEquals(
                1,
                stack.poll()
        );
        assertEquals(
                0,
                stack.getSize()
        );
    }

}