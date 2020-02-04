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
        ListContainer<Integer> listContainer = new ListContainer<>();
        for (int i = 0; i < 250; i++) {
            listContainer.add(i);
        }
        assertThat(listContainer.getSize(), is(250));
    }

    @Test
    public void get() {
        ListContainer<Integer> listContainer = new ListContainer<>();
        listContainer.add(1);
        listContainer.add(2);
        listContainer.add(3);
        listContainer.add(4);
        assertThat(listContainer.get(0), is(1));
        assertThat(listContainer.get(1), is(2));
        assertThat(listContainer.get(2), is(3));
        assertThat(listContainer.get(3), is(4));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getIndexOutOfBoundsException() {
        ListContainer<Integer> listContainer = new ListContainer<>();
        listContainer.get(0);
        listContainer.add(1);
        assertThat(listContainer.get(0), is(1));
        listContainer.get(0);
    }

    @Test
    public void iterator() {
        ListContainer<Integer> listContainer = new ListContainer<>();
        listContainer.add(1);
        listContainer.add(2);
        listContainer.add(3);
        Iterator<Integer> it = listContainer.iterator();
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
        ListContainer<Integer> listContainer = new ListContainer<>();
        listContainer.add(1);
        listContainer.add(2);
        listContainer.add(3);
        Iterator<Integer> it = listContainer.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void iteratorConcurrentModificationException() {
        ListContainer<Integer> listContainer = new ListContainer<>();
        listContainer.add(1);
        listContainer.add(2);
        listContainer.add(3);
        Iterator<Integer> it = listContainer.iterator();
        listContainer.add(4);
        it.next();
    }

}