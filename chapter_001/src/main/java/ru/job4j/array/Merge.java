package ru.job4j.array;

import java.util.Arrays;

public class Merge {

    public int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int iLeft = 0, iRight = 0;

        for (int i = 0; i < result.length; i++) {
            if (iLeft == left.length) {
                result[i] = right[iRight];
                iRight++;
            } else {
                if (iRight == right.length) {
                    result[i] = left[iLeft];
                    iLeft++;
                } else {
                    if (left[iLeft] < right[iRight]) {
                        result[i] = left[iLeft];
                        iLeft++;
                    } else {
                        result[i] = right[iRight];
                        iRight++;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Merge process = new Merge();
        int[] rsl = process.merge(
                new int[] {1, 3, 5}, //left
                new int[] {2, 4}  //right
        );
        System.out.println(Arrays.toString(rsl));
    }

}