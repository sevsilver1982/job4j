package inout.examples;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParseURL {

    public static void main(String[] args) {
        String url = "GET /?msg=1234567890&qwe=321 HTTP/1.1";
        Map<String, String> result =
                Stream.of(url.split("\\?")[1])
                .flatMap(params -> Stream.of(params.split("&")))
                        .flatMap(s -> Stream.of(s.contains(" ") ? s.substring(0, s.indexOf(" ")) : s))
                        .map(s -> s.split("="))
                        .collect(
                                Collectors.toMap(
                                        p -> p[0],
                                        p1 -> p1[1])
                        );
        result.forEach((key, value) -> System.out.printf("%s = %s\n", key, value));
    }

}
