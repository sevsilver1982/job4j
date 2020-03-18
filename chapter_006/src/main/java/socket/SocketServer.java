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
    private final SimpleLogger log = new SimpleLogger(System.out);
    private final int port;
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    /**
     * Incoming request handler predicate.
     */
    private final Predicate<String> testRequest = request -> {
        log.writeln(String.format("received: %s", request));
        return true;
    };

    /**
     * Outgoing response preparation function.
     */
    private final Function<String, String> prepareResponse = request -> {
        String preparedResponse = String.format("echo server received: %s", request);
        out.println(preparedResponse);
        return preparedResponse;
    };

    /**
     * SocketServer constructor.
     * @param port the port number.
     */
    public SocketServer(int port) {
        this.port = port;
    }

    /**
     * Server listener.
     * @throws IOException
     * @throws IllegalStateException
     */
    public void setListener() throws IOException, IllegalStateException {
        try {
            socket = serverSocket.accept();
            log.writeln("new connection");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            new Inout<>(in, out, testRequest, prepareResponse);
        } catch (IllegalStateException e) {
            log.writeln("connection closed");
            setListener();
        }
    }

    /**
     * Init server.
     */
    public void init() {
        try {
            serverSocket = new ServerSocket(port);
            setListener();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SocketServer(777).init();
    }

}