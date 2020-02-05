package list.container;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ArrayContainerIterableTest {

    @Test
    public void add() {
        ArrayContainerIterable<Integer> list = new ArrayContainerIterable<>();
        for (int i = 0; i < 250; i++) {
            list.add(i);
        }
        assertThat(list.getSize(), is(250));
    }

    @Test
    public void get() {
        ArrayContainerIterable<Integer> list = new ArrayContainerIterable<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(3));
        assertThat(list.get(3), is(4));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getIndexOutOfBoundsException() {
        ArrayContainerIterable<Integer> list = new ArrayContainerIterable<>();
        list.get(0);
        list.add(1);
        assertThat(list.get(0), is(1));
        list.get(0);
    }

    @Test
    public void iterator() {
        ArrayContainerIterable<Integer> list = new ArrayContainerIterable<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorNoSuchElementException() {
        ArrayContainerIterable<Integer> list = new ArrayContainerIterable<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> it = list.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void iteratorConcurrentModificationException() {
        ArrayContainerIterable<Integer> list = new ArrayContainerIterable<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> it = list.iterator();
        list.add(4);
        it.next();
    }

}