package inout.chat;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

public class Inout<T1 extends Reader, T2 extends Writer> {
    private Scanner scanner;
    private T2 out;

    /**
     * Inout constructor
     * @param in input stream.
     * @param out output stream.
     * @param testRequest incoming request handler.
     * @param prepareResponse prepare outgoing response.
     */
    public Inout(T1 in, T2 out, Predicate<String> testRequest, Function<String, String> prepareResponse) throws IOException {
        this.scanner = new Scanner(in);
        this.out = out;
        loop(testRequest, prepareResponse);
    }

    /**
     * Main loop.
     * @param testRequest incoming request handler predicate.
     * @param prepareResponse preparation outgoing response function.
     */
    private void loop(Predicate<String> testRequest, Function<String, String> prepareResponse) throws IOException, IllegalStateException {
        String request;
        boolean result;
        try {
            do {
                if (!scanner.hasNext()) {
                    throw new IllegalStateException();
                }
                request = scanner.nextLine();
                result = testRequest.test(request);
                if (result) {
                    out.write(prepareResponse.apply(request));
                    out.flush();
                }
            } while (result);
        } catch (IOException e) {
            throw new IOException();
        }
    }

}