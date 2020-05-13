package examples;

import java.util.ArrayList;
import java.util.List;

public class ConvertList {

    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] row : list) {
            for (int cell : row) {
                result.add(cell);
            }
        }
        return result;
    }

    public int[][] toArray(List<Integer> list, int cells) {
        int groups = (int) Math.ceil((double) list.size() / cells);
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

}