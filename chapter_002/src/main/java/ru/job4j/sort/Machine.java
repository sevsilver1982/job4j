package ru.job4j.sort;

import java.util.Arrays;

public class Machine {
    private final int[] coins = {10, 5, 2, 1};

    public int[] change(int money, int price) {
        int[] result = new int[100];
        int size = 0;
        int exchange = money - price;
        try {
            if (exchange < 0) {
                throw new RuntimeException("Недостаточно средств");
            }
            while (exchange > 0) {
                for (int coin : coins) {
                    if (exchange - coin >= 0) {
                        exchange = exchange - coin;
                        result[size++] = coin;
                        break;
                    }
                }
            }
            result = Arrays.copyOf(result, size);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

}