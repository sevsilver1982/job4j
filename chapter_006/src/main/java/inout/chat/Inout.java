package inout.chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

public class Inout {
    private Scanner scanner;
    private OutputStream out;

    /**
     * Inout constructor
     * @param in input stream.
     * @param out output stream.
     * @param testRequest incoming request handler.
     * @param prepareResponse prepare outgoing response.
     */
    public Inout(InputStream in, OutputStream out, Predicate<String> testRequest, Function<String, String> prepareResponse) {
        this.scanner = new Scanner(in);
        this.out = out;
        loop(testRequest, prepareResponse);
    }

    /**
     * Main loop.
     * @param testRequest incoming request handler predicate.
     * @param prepareResponse preparation outgoing response function.
     */
    private void loop(Predicate<String> testRequest, Function<String, String> prepareResponse) {
        String request;
        boolean result;
        do {
            if (!scanner.hasNext()) {
                throw new IllegalStateException();
            }
            request = scanner.nextLine();
            result = testRequest.test(request);
            if (result) {
                try {
                    out.write(prepareResponse.apply(request).getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } while (result);
    }

}