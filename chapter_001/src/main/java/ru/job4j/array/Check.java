package ru.job4j.array;

public class Check {
    /**
     * Проверить, что все элементы в массиве являются или true или false
     * @param data Массив
     * @return true - если массив однородный, иначе false.
     */
    public boolean mono(boolean[] data) {
        boolean result = true;

        boolean reference = data[0];
        for (int index = 0; index != data.length; index++) {
            if (data[index] != reference) {
                result = false;
                break;
            }
        }
        return result;
    }

}
