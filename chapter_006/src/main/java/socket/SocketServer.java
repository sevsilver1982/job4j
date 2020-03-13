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

    /**
     * Incoming request handler predicate.
     */
    private Predicate<String> testRequest = request -> {
        log.writeln(String.format("server received request: %s", request));
        return true;
    };

    /**
     * Outgoing response preparation function.
     */
    private Function<String, String> prepareResponse = request -> {
        String preparedResponse = String.format("echo server received request: %s", request);
        out.println(preparedResponse);
        return preparedResponse;
    };

    public SocketServer(int port) {
        this.port = port;
    }

    public void setNewListener() throws IOException, IllegalStateException {
        try {
            socket = serverSocket.accept();
            log.writeln("new connection");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            new Inout<>(in, out, testRequest, prepareResponse);
        } catch (IllegalStateException e) {
            log.writeln("connection closed");
            setNewListener();
        }
    }

    public void init() {
        try {
            serverSocket = new ServerSocket(port);
            setNewListener();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SocketServer(777).init();
    }

}