package examples.loop;

public class Mortgage {

    public int year(int amount, int salary, double percent) {
        int year = 0;
        double credit = amount;

        while (credit > 0) {
            credit = (credit + (credit * (percent / 100))) - salary;
            System.out.println(credit);
            year++;
        }

        return year;
    }

}