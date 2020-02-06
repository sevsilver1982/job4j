package list;

import list.LinkedContainerIterable;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LinkedContainerIterableTest {

    @Test
    public void add() {
        LinkedContainerIterable<Integer> list = new LinkedContainerIterable<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(3));
    }

    @Test
    public void get() {
        LinkedContainerIterable<Integer> list = new LinkedContainerIterable<>();
        list.add(1);
        assertThat(list.get(0), is(1));
        list.add(2);
        assertThat(list.get(1), is(2));
        list.add(3);
        assertThat(list.get(2), is(3));
    }

    @Test
    public void iterator() {
        LinkedContainerIterable<Integer> list = new LinkedContainerIterable<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
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
        LinkedContainerIterable<Integer> list = new LinkedContainerIterable<>();
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
        LinkedContainerIterable<Integer> list = new LinkedContainerIterable<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> it = list.iterator();
        list.add(4);
        it.next();
    }

}