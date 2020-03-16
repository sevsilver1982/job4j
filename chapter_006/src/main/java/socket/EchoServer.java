package socket;

import inout.chat.Inout;
import inout.logger.SimpleLogger;

import java.io.*;
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
        log.writeln("send response");
        String preparedResponse = "HTTP/1.1 200 OK\r\n\r\nHello, dear friend.";
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
            while (!serverSocket.isClosed()) {
                socket = serverSocket.accept();
                log.writeln("new connection");
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream());
                new Inout<>(in, out, testRequest, prepareResponse);
            }
        } catch (IllegalStateException e) {
            log.writeln("connection closed");
            setListener();
        }
    }

    public void init() {
        try {
            serverSocket = new ServerSocket(port);
            log.writeln("start server");
            setListener();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inita() throws IOException {
        log.writeln("start server");
        ServerSocket server = new ServerSocket(9000);

        while (server.isBound()) {
            Socket socket = server.accept();
            log.writeln("new connection");
            try (
                    OutputStream out = socket.getOutputStream();
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
            ) {
                log.writeln("read request");
                StringBuilder stringBuilder = new StringBuilder();
                String request;
                String key = "";
                String value = "";
                String response = "";
                while (!(request = in.readLine()).isEmpty()) {

                    if (request.startsWith("GET") && request.contains("?")) {



                        String[] array = request.split("\\?");
                        //key =  //.substring(1, request.indexOf(" "));
                        log.writeln(response);
                    }

                    stringBuilder.append(request);
                }
                if (!(request = stringBuilder.toString()).isEmpty()) {
                    log.writeln(request);
                    log.writeln("send response");
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write("Hello, dear friend.".getBytes());
                }
            }
            log.writeln("connection close");
        }
    }

    public static void main(String[] args) throws IOException {
        new EchoServer(9000).inita();
    }

}