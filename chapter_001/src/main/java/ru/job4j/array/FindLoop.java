package ru.job4j.array;

public class FindLoop {

    /**
     * Поиск элемента в массиве
     * @param data Массив
     * @param el Элемент
     * @return Индекс элемента, если элемент не найден, иначе -1.
     */
    public int indexOf(int[] data, int el) {
        int rst = -1;
        for (int index = 0; index != data.length; index++) {
            if (data[index] == el) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    /**
     * Поиск элемента в диапазоне массива
     * @param data Массив
     * @param el Элемент
     * @param start начало диапазона
     * @param finish конец диапазона
     * @return Индекс элемента, если элемент не найден, иначе -1.
     */
    public static int indexOf(int[] data, int el, int start, int finish) {
        int rst = -1;
        for (int index = start; index < finish; index++) {
            if (data[index] == el) {
                rst = index;
                break;
            }
        }
        return rst;
    }

}
