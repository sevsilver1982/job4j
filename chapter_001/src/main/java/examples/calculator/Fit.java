package examples.calculator;

public class Fit {

    public static double manWeight(double height) {
        return (height - 100) * 1.15;
    }

    public static double womanWeight(double height) {
        return (height - 110) * 1.15;
    }

    public static void main(String[] args) {
        double man = manWeight(184);
        System.out.println("Man 184 is " + man);
        double woman = womanWeight(180);
        System.out.println("Woman 180 is " + woman);
    }

}
