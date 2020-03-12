package socket;

import inout.chat.Inout;
import inout.logger.SimpleLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Function;
import java.util.function.Predicate;

public class SocketServer {
    private SimpleLogger log = new SimpleLogger(System.out);
    private final int port;
    private ServerSocket serverSocket;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public SocketServer(int port) {
        this.port = port;
    }

    /**
     * Incoming request handler predicate.
     */
    public Predicate<String> testRequest = request -> {
        log.writeln(String.format("server received request: %s", request));
        return true;
    };

    /**
     * Outgoing response preparation function.
     */
    Function<String, String> prepareResponse = request -> {
        String preparedResponse = String.format("echo server received request: %s", request);
        out.println(preparedResponse);
        return preparedResponse;
    };

    public void init() throws IOException, IllegalStateException {
        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            log.writeln("new connection");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            new Inout<>(in, out, testRequest, prepareResponse);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            System.out.println("init2");
            socket = serverSocket.accept();
            log.writeln("new connection");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            new Inout<>(in, out, testRequest, prepareResponse);
        }
    }

    public static void main(String[] args) throws IOException {
        new SocketServer(777).init();
    }

}