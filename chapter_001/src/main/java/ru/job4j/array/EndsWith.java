package ru.job4j.array;

public class EndsWith {

    /**
     * Проверка, что массив word имеет последние элементы одинаковые с последовательностью post
     * @param word Массив
     * @param post последовательность
     * @return Вернёт true, если массив заканчивается последовательностью post
     */
    public static boolean endsWith(char[] word, char[] post) {
        boolean result = true;
        for (int index = 0; index < post.length; index++) {
            if (word[word.length - post.length + index] != post[index]) {
                result = false;
                break;
            }
        }
        return result;
    }

}
