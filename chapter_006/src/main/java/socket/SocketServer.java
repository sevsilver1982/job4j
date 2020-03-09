package socket;

import inout.logger.SimpleLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class SocketServer {
    private SimpleLogger logger;
    private Socket socket;
    private final int port;
    private PrintWriter out;
    private BufferedReader in;

    public SocketServer(int port) {
        this.port = port;
    }

    public void listen()  {
        String ask = "";
        while (socket.isConnected()) {
            logger.log("wait command...");
            try {
                ask = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.log(ask);
            out.write(Arrays.toString("HTTP/1.1 200 OK\r\n".getBytes()));
            out.write(Arrays.toString("Hello, dear friend.".getBytes()));
        }
    }

    public void start() {
        logger = new SimpleLogger(System.out);
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            logger.log("new connection");
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SocketServer(9000).start();
    }

}