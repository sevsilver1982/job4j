package ru.job4j.array;

public class Turn {

    /**
     * Перевернуть массив задом наперёд
     * @param array Массив
     * @return Массив задом наперёд
     */
    public int[] back(int[] array) {
        int tmp = 0;
        int i = 0;
        int half = (array.length / 2);

        if (half % 2 == 0) half--;
        for (int index = 0; index <= half; index++) {
            i = array.length - 1 - index;
            tmp = array[index];
            array[index] = array[i];
            array[i] = tmp;
        }
        return array;
    }

}
