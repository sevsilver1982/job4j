package ru.job4j.collection;

import java.util.Arrays;
import java.util.List;

public class ConvertList2Array {

    public static int[][] toArray(List<Integer> list, int cells) {
        int groups = (int) Math.ceil((double) list.size() / cells);
        //System.out.println(groups);
        int row = 0, cell = 0;
        int[][] array = new int[groups][cells];
        for (Integer num : list) {
            array[row][cell++] = num;
            if (cell == cells) {
                cell = 0;
                row++;
            }
        }
        return array;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        int[][] rsl = toArray(list, 3);
        for (int[] row : rsl) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

}
