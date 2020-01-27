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
        for (int index = position; index < ints.length; index++) {
            if (ints[index] % 2 == 0) {
                position = index;
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            return ints[position++];
        } else {
            throw new NoSuchElementException();
        }
    }

}