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
        boolean result = true;
        if (ypos > this.ints.length - 1 || xpos > this.ints[ypos].length - 1) {
            result = false;
        }
        return result;
    }

    @Override
    public Object next() {
        int i = this.ints[ypos][xpos];
        if (xpos < this.ints[ypos].length - 1) {
            xpos++;
        } else {
            xpos = 0;
            ypos++;
        }
        return i;
    }

}