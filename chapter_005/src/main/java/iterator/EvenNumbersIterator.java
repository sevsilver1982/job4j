package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
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
                position = i;
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return ints[position++];
    }

}