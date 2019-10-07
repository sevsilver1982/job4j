package ru.job4j.array;

public class Square {

    public int[] calculate(int bound) {
        int[] rst = new int[bound];
        int counter = 1;

        for (int i = 0; i != bound ; i++) {
            rst[i] = counter * counter;
            counter++;
        }

        // заполнить массив через цикл элементами от 1 до bound возведенными в квадрат
        return rst;
    }

}
