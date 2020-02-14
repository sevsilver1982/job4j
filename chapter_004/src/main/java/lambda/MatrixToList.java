package lambda;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MatrixToList {
    static int position = 1;

    public static void main(String[] args) {

        Integer[][] matrix = new Integer[][] {
                {0, 0, 1, 0, 0},
                {0, 2, 0, 3, 0},
                {4, 0, 5, 0, 9}
        };

        Stream.of(matrix)
                .flatMap(Stream::of)
                .collect(Collectors.toList())
                .forEach(x -> System.out.printf("row %s = %s\n", position++, x));
    }

}