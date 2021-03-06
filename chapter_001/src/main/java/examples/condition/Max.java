package examples.condition;

public class Max {

    public static int max(int first, int second) {
        return first > second ? first : second;
    }

    public static int max(int first, int second, int third) {
        return third > max(first, second) ? third : max(first, second);
    }

    public static int max(int first, int second, int third, int fourth) {
        int n = third > max(first, second) ? third : max(first, second);
        return fourth > n ? fourth : n;
    }

}