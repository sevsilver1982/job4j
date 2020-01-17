package math;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MathUtil {

    public static double add(int left, int second) {
        return left + second;
    }

    public static double div(int left, int second) {
        return left / second;
    }

    public List<Double> diapason(int start, int end, Function<Double, Double> func) {
        return IntStream
                .range(start, end)
                .mapToObj(i -> func.apply((double) i))
                .collect(Collectors.toList());
    };

}