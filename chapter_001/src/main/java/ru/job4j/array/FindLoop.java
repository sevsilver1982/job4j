package ru.job4j.array;

public class FindLoop {

    /**
     * Поиск минимального значения в диапазоне массива data
     * @param data
     * @param start Начало диапазона
     * @param finish Конец диапазона
     * @return Минимальное значение элемента в массиве
     */
    public static int findMin(int[] data, int start, int finish) {
        int rst = data[start];
        for (int index = start; index <= finish; index++) {
            if (data[index] < rst) {
                rst = data[index];
            }
        }
        return rst;
    }

    /**
     * Поиск int в массиве
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
     * Поиск int в диапазоне массива
     * @param data Массив
     * @param el Элемент
     * @param start Начало диапазона
     * @param finish Конец диапазона
     * @return Индекс элемента, если элемент не найден, иначе -1.
     */
    public static int indexOf(int[] data, int el, int start, int finish) {
        int rst = -1;
        for (int index = start; index <= finish; index++) {
            if (data[index] == el) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    /**
     * Поиск char в массиве
     * @param data Массив
     * @param el Элемент
     * @return Индекс элемента, если элемент не найден, иначе -1.
     */
    public static int indexOf(char[] data, char el) {
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
     * Поиск char в диапазоне массива
     * @param data Массив
     * @param el Элемент
     * @return Индекс элемента, если элемент не найден, иначе -1.
     */
    public static int indexOf(char[] data, char el, int start, int finish) {
        int rst = -1;
        for (int index = start; index <= finish; index++) {
            if (data[index] == el) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    /**
     * Сортировка массива data
     * @param data Массив
     * @return Отсортированный по возрастанию массив
     */
    public static int[] sort(int[] data) {
        int tmp = 0;
        int minVal = 0;
        int minIndex;
        for (int i = 0; i < data.length; i++) {
            minVal = findMin(data, i, data.length - 1);
            minIndex = indexOf(data, minVal, i, data.length - 1);
            tmp = data[i]; /* if != */
            data[i] = data[minIndex];
            data[minIndex] = tmp;
        }
        return data;
    }

}