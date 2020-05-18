package socket;

import inout.logger.SimpleLogger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Usage:
 * http://localhost:9000/?msg=Hello
 */
public class EchoServer {
    private final SimpleLogger log = new SimpleLogger(System.out);
    private final int port;

    /**
     * EchoServer constructor.
     * @param port the port number.
     */
    public EchoServer(int port) {
        this.port = port;
    }

    public Map<String, String> getRequestParams(String request) {
        return Stream.of(request.split("\\?")[1])
                .flatMap(params -> Stream.of(params.split("&")))
                .flatMap(s -> Stream.of(s.contains(" ") ? s.substring(0, s.indexOf(" ")) : s))
                .map(s -> s.split("="))
                .collect(
                        Collectors.toMap(
                                key -> key[0],
                                value -> value[1]
                        )
                );
    }

    /**
     * Init server.
     */
    public void init() {
        log.writeln("start server");
        try (ServerSocket server = new ServerSocket(port)) {
            while (!server.isClosed()) {
                try (Socket socket = server.accept();
                     OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
                ) {
                    String request = in.readLine();
                    if (request != null && request.startsWith("GET /?")) {
                        String msg = getRequestParams(request).getOrDefault("msg", "");
                        switch (msg.toLowerCase()) {
                            case "buy":
                            case "exit":
                                log.writeln("server shutdown");
                                out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                                out.write("server shutdown".getBytes());
                                server.close();
                                break;
                            default:
                                log.writeln(String.format("echo response: %s", msg));
                                out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                                out.write(msg.getBytes());
                                break;
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace(new PrintStream(log.getOutput()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace(new PrintStream(log.getOutput()));
        }
    }

    public static void main(String[] args) {
        new EchoServer(9000).init();
    }

}