package iterator;

import java.util.Arrays;
import java.util.Iterator;

public class MatrixIterator implements Iterator {
    private int[][] ints;
    private int xpos = 0;
    private int ypos = 0;

    public MatrixIterator(int[][] ints) {
        this.ints = Arrays.copyOf(ints, ints.length);
    }

    @Override
    public boolean hasNext() {
        boolean result = true;
        if (this.ypos > this.ints.length - 1 || this.xpos > this.ints[this.ypos].length - 1) {
            result = false;
        }
        return result;
    }

    @Override
    public Object next() {
        int i = this.ints[this.ypos][this.xpos];
        if (this.xpos < this.ints[this.ypos].length - 1) {
            this.xpos++;
        } else {
            this.xpos = 0;
            this.ypos++;
        }
        return i;
    }

}