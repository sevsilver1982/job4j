package ru.job4j.array;

public class ArrayChar {

    /**
     * Проверка, что массив word начинается c последовательности pref.
     * @param word Массив
     * @param pref Последовательность
     * @return Вернёт true, если массив начинается с последовательности pref, иначе false
     */
    public static boolean startsWith(char[] word, char[] pref) {
        boolean result = true;
        for (int index = 0; index != pref.length; index++) {
            if (word[index] != pref[index]) {
                result = false;
                break;
            }
        }
        return result;
    }

}
