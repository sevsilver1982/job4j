package simple;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleQueueTest {

    @Test
    public void all() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        assertThat(queue.getSize(), is(1));
        queue.push(2);
        assertThat(queue.getSize(), is(2));
        queue.push(3);
        assertThat(queue.getSize(), is(3));

        assertThat(queue.poll(), is(1));
        assertThat(queue.getSize(), is(2));
        assertThat(queue.poll(), is(2));
        assertThat(queue.getSize(), is(1));
        assertThat(queue.poll(), is(3));
        assertThat(queue.getSize(), is(0));
    }

}