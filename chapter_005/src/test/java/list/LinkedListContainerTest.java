package list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LinkedListContainerTest {

    @Test
    public void add() {
        LinkedListContainer<Integer> list = new LinkedListContainer<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(3));
    }

    @Test
    public void get() {
        LinkedListContainer<Integer> list = new LinkedListContainer<>();
        list.add(1);
        assertThat(list.get(0), is(1));
        list.add(2);
        assertThat(list.get(1), is(2));
        list.add(3);
        assertThat(list.get(2), is(3));
    }

    @Test
    public void iterator() {
        LinkedListContainer<Integer> list = new LinkedListContainer<>();
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

    @Test
    public void iteratorNoSuchElementException() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            LinkedListContainer<Integer> list = new LinkedListContainer<>();
            list.add(1);
            list.add(2);
            list.add(3);
            Iterator<Integer> it = list.iterator();
            assertThat(it.next(), is(1));
            assertThat(it.next(), is(2));
            assertThat(it.next(), is(3));
            it.next();
        });
    }

    @Test
    public void iteratorConcurrentModificationException() {
        Assertions.assertThrows(ConcurrentModificationException.class, () -> {
            LinkedListContainer<Integer> list = new LinkedListContainer<>();
            list.add(1);
            list.add(2);
            list.add(3);
            Iterator<Integer> it = list.iterator();
            list.add(4);
            it.next();
        });
    }

}