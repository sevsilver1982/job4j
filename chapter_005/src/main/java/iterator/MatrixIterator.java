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
        return this.ypos <= this.ints.length - 1 && this.xpos <= this.ints[this.ypos].length - 1;
    }

    @Override
    public Object next() {
        int i = this.ints[this.ypos][this.xpos++];
        if (this.xpos > this.ints[this.ypos].length - 1) {
            this.xpos = 0;
            this.ypos++;
        }
        return i;
    }

}