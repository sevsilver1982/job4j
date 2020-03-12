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
    private SimpleLogger log;
    private Socket socket;
    private final int port;
    private PrintWriter out;
    private BufferedReader in;

    public SocketServer(int port) {
        this.port = port;
    }

    /*public void listen()  {
        String ask = "";
        while (socket.isConnected()) {
            log.writeln("wait command...");
            try {
                ask = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.write(ask);
        }
    }*/

    /**
     * Incoming request handler predicate.
     */
    public Predicate<String> testRequest = request -> {
        log.writeln(request);
        return true;
    };

    /**
     * Outgoing response preparation function.
     */
    Function<String, String> prepareResponse = request -> {
        String preparedResponse = request;
        log.write(preparedResponse);
        return preparedResponse;
    };

    public void init() {
        log = new SimpleLogger(System.out);
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            log.writeln("new connection");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            new Inout<>(in, out, testRequest, prepareResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SocketServer(777).init();
    }

}