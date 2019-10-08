package ru.job4j.array;

public class FindLoop {

    /**
     * Поиск элемента в массиве
     * @param data Массив
     * @param el Элемент
     * @return Индекс найденного элемента. Если элемент не найден, то метод вернёт -1.
     */
    public int indexOf(int[] data, int el) {
        int rst = -1; // если элемента нет в массиве, то возвращаем -1.
        for (int index = 0; index != data.length; index++) {
            if (data[index] == el) {
                rst = index;
                break;
            }
        }
        return rst;
    }

}
