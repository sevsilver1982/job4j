package iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Converter {

    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        List<Integer> list = new ArrayList<>();
        it.forEachRemaining(x ->
                x.forEachRemaining(list::add));
        return list.iterator();
    }

}