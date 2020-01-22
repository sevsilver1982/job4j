package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator {
    private int[] ints;
    private int position = 0;

    public EvenNumbersIterator(int[] ints) {
        this.ints = ints;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = position; i < ints.length; i++) {
            if (ints[i] % 2 == 0) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Object next() {
        int result = 0;
        for (int i = position; i < ints.length; i++) {
            position++;
            if (ints[i] % 2 == 0) {
                result = ints[i];
                break;
            }
            if (position == ints.length) {
                throw new NoSuchElementException();
            }
        }
        return result;

    }

}