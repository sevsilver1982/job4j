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

public class EchoServer {
    private SimpleLogger log = new SimpleLogger(System.out);
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
        String preparedResponse = "HTTP/1.1 200 OK\r\nллл\r\n";
        //out.println(preparedResponse);
       // out.println("");
        //out.write("");
        return preparedResponse;
    };

    /**
     * EchoServer constructor.
     * @param port the port number.
     */
    public EchoServer(int port) {
        this.port = port;
    }

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

    public void init() {
        try {
            serverSocket = new ServerSocket(port);
            setListener();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inita() throws IOException {
        //System.out.println("start----------------------------");
        /*try (ServerSocket server = new ServerSocket(9000)) {
            while (true) {
                Socket socket = server.accept();
                System.out.println("read----------------------------");
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {

                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                    }
                    System.out.println("write----------------------------");
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write("Hello, dear friend.".getBytes());
                }
            }
        }*/
    }

    public static void main(String[] args) throws IOException {
        new EchoServer(9000).init();
    }

}