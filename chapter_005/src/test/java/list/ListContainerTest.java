package list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ListContainerTest {

    @Test
    public void add() {
        ListArrayContainer<Integer> listArrayContainer = new ListArrayContainer<>();
        for (int i = 0; i < 250; i++) {
            listArrayContainer.add(i);
        }
        assertThat(listArrayContainer.getSize(), is(250));
    }

    @Test
    public void get() {
        ListArrayContainer<Integer> listArrayContainer = new ListArrayContainer<>();
        listArrayContainer.add(1);
        listArrayContainer.add(2);
        listArrayContainer.add(3);
        listArrayContainer.add(4);
        assertThat(listArrayContainer.get(0), is(1));
        assertThat(listArrayContainer.get(1), is(2));
        assertThat(listArrayContainer.get(2), is(3));
        assertThat(listArrayContainer.get(3), is(4));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getIndexOutOfBoundsException() {
        ListArrayContainer<Integer> listArrayContainer = new ListArrayContainer<>();
        listArrayContainer.get(0);
        listArrayContainer.add(1);
        assertThat(listArrayContainer.get(0), is(1));
        listArrayContainer.get(0);
    }

    @Test
    public void iterator() {
        ListArrayContainer<Integer> listArrayContainer = new ListArrayContainer<>();
        listArrayContainer.add(1);
        listArrayContainer.add(2);
        listArrayContainer.add(3);
        Iterator<Integer> it = listArrayContainer.iterator();
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
        ListArrayContainer<Integer> listArrayContainer = new ListArrayContainer<>();
        listArrayContainer.add(1);
        listArrayContainer.add(2);
        listArrayContainer.add(3);
        Iterator<Integer> it = listArrayContainer.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void iteratorConcurrentModificationException() {
        ListArrayContainer<Integer> listArrayContainer = new ListArrayContainer<>();
        listArrayContainer.add(1);
        listArrayContainer.add(2);
        listArrayContainer.add(3);
        Iterator<Integer> it = listArrayContainer.iterator();
        listArrayContainer.add(4);
        it.next();
    }

}