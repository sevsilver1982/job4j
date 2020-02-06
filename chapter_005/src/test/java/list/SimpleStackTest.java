package list;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleStackTest {

    @Test
    public void all() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        assertThat(stack.getSize(), is(1));
        stack.push(2);
        assertThat(stack.getSize(), is(2));
        stack.push(3);
        assertThat(stack.getSize(), is(3));

        assertThat(stack.poll(), is(3));
        assertThat(stack.getSize(), is(2));
        assertThat(stack.poll(), is(2));
        assertThat(stack.getSize(), is(1));
        assertThat(stack.poll(), is(1));
        assertThat(stack.getSize(), is(0));
    }

}