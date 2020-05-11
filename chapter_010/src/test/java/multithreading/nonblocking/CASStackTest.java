package multithreading.nonblocking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CASStackTest {

    @Test
    public void when3PushThen3Poll() throws InterruptedException {
        CASStack<Integer> stack = new CASStack<>();
        Thread thread1 = new Thread(() -> stack.push(1));
        Thread thread2 = new Thread(() -> stack.push(2));
        Thread thread3 = new Thread(() -> stack.push(3));
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        List<Integer> result = new ArrayList<>();
        result.add(stack.poll());
        result.add(stack.poll());
        result.add(stack.poll());
        result.sort(Comparator.naturalOrder());
        assertEquals(
                List.of(1, 2, 3),
                result
        );
    }

    @Test
    public void when1PushThen1Poll() {
        CASStack<Integer> stack = new CASStack<>();
        stack.push(1);
        assertEquals(1, stack.poll());
    }

    @Test
    public void when2PushThen2Poll() {
        CASStack<Integer> stack = new CASStack<>();
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.poll());
        assertEquals(1, stack.poll());
    }

}