package generic.list;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleArrayTest {

    @Test
    public void add() {
        SimpleArray<Integer> objects = new SimpleArray<>(5);
        objects.add(1);
        assertThat(objects.get(0), is(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void set() {
        SimpleArray<Integer> objects = new SimpleArray<>(5);
        objects.add(1);
        assertThat(objects.get(0), is(1));
        objects.set(0, 2);
        assertThat(objects.get(0), is(2));
        objects.set(1, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void remove() {
        SimpleArray<Integer> objects = new SimpleArray<>(5);
        objects.add(0);
        objects.add(1);
        objects.add(2);
        objects.add(3);
        objects.add(4);
        objects.remove(1);
        objects.remove(1);
        objects.remove(1);
        objects.add(9);
        assertThat(objects.get(0), is(0));
        assertThat(objects.get(1), is(4));
        assertThat(objects.get(2), is(9));
        objects.get(3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get() {
        SimpleArray<Integer> objects = new SimpleArray<>(5);
        objects.add(1);
        objects.add(2);
        objects.add(3);
        assertThat(objects.get(0), is(1));
        assertThat(objects.get(1), is(2));
        assertThat(objects.get(2), is(3));
        objects.get(3);
    }

    @Test
    public void iterator() {
        SimpleArray<Integer> objects = new SimpleArray<>(5);
        objects.add(1);
        objects.add(2);
        objects.add(3);
        objects.add(4);
        objects.add(5);
        Iterator<Integer> it = objects.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorNoSuchElementException() {
        SimpleArray<Integer> objects = new SimpleArray<>(5);
        objects.add(1);
        objects.add(2);
        objects.add(3);
        Iterator<Integer> it = objects.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test
    public void getIndexByObject() {
    }

    @Test
    public void getIndexById() {
    }
}