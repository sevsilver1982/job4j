package ru.job4j.loop;

public class PrimeNumber {

    public int calc(int finish) {
        boolean prime = false;
        int count = 0;

        for (int j = 0; j <= finish; j++) {
            if ((j > 1) && (j % 1 == 0) && (j % j == 0)) {
                prime = true;
                for (int i = 2; i <= j - 1; i++) {
                    if (j % i == 0) {
                        prime = false;
                        break;
                    }
                }
            }
            if (prime) count++;
        }

        return count;
    }

}
