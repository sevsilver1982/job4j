package examples.loop;

public class PrimeNumber {

    public int calc(int finish) {
        int count = 0;

        CheckPrimeNumber prime = new CheckPrimeNumber();
        for (int j = 0; j <= finish; j++) {
            if (prime.check(j)) {
                count++;
            }
        }

        return count;
    }

}
