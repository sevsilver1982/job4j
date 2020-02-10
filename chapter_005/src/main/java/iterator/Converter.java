package iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter implements Iterator<Integer> {
    private Iterator<Iterator<Integer>> it;
    private Iterator<Integer> iterator = new ArrayList().iterator();

    public Converter(Iterator<Iterator<Integer>> it) {
        this.it = it;
    }

    public Iterator<Integer> convert() {
        return this;
    }

    @Override
    public boolean hasNext() {
        while (it.hasNext() && !iterator.hasNext()) {
            iterator = it.next();
        }
        return iterator.hasNext();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return iterator.next();
    }

}