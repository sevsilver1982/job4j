package ru.job4j;

import java.util.Arrays;

public class Machine {
    private final int[] coins = {10, 5, 2, 1};

    public int[] change(int money, int price) {
        int[] result = new int[100];
        int size = 0;
        int pos = 0;
        int exchange = money - price;
        while (exchange > 0) {
            if (exchange - coins[pos] >= 0) {
                exchange = exchange - coins[pos];
                result[size++] = coins[pos];
            } else {
                pos++;
            }
        }
        return Arrays.copyOf(result, size);
    }

}