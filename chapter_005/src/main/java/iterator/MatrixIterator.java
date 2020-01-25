package iterator;

import java.util.Iterator;

public class MatrixIterator implements Iterator {
    private int[][] ints;
    private int xpos = 0;
    private int ypos = 0;

    public MatrixIterator(int[][] ints) {
        this.ints = ints;
    }

    @Override
    public boolean hasNext() {
        return ypos <= ints.length - 1 && xpos <= ints[ypos].length - 1;
    }

    @Override
    public Object next() {
        int i = ints[ypos][xpos++];
        if (xpos > ints[ypos].length - 1) {
            xpos = 0;
            ypos++;
        }
        return i;
    }

}