package ru.job4j.loop;

public class CheckPrimeNumber {

    public boolean check(int finish) {
        boolean prime = false;

        if ((finish > 1) && (finish % 1 == 0) && (finish % finish == 0)) {
            prime = true;
            for (int i = 2; i < finish - 1; i++) {
                if (finish % i == 0) {
                    prime = false;
                    break;
                }
            }
        }

        return prime;
    }

}
