package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class EvenNumbersIterator implements Iterator {
    private int[] ints;
    private int position = 0;

    public EvenNumbersIterator(int[] ints) {
        this.ints = ints;
    }

    @Override
    public boolean hasNext() {
        for (int i = this.position; i < this.ints.length; i++) {
            if (this.ints[i] % 2 == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object next() {
        for (int i = this.position; i < this.ints.length; i++) {
            this.position++;
            if (this.ints[i] % 2 == 0) {
                return this.ints[i];
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void forEachRemaining(Consumer action) {
        throw new UnsupportedOperationException();
    }

}