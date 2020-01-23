package iterator;

import java.util.*;

public class Converter implements Iterator<Integer> {
    private Iterator<Iterator<Integer>> iterator;
    private Iterator<Integer> ptrNextIterator = null;

    public Converter(Iterator<Iterator<Integer>> iterator) {
        this.iterator = iterator;
    }

    public Iterator<Integer> convert() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext() || ptrNextIterator.hasNext();
    }

    @Override
    public Integer next() {
        while (ptrNextIterator == null || !ptrNextIterator.hasNext()) {
            ptrNextIterator = iterator.next();
        }
        return ptrNextIterator.next();
    }

}