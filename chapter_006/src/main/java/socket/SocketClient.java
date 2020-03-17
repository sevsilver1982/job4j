package socket;

import inout.logger.SimpleLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
    private SimpleLogger log = new SimpleLogger(System.out);
    Scanner scanner = new Scanner(System.in);
    private String ip;
    private int port;
    private Socket socket;
    BufferedReader in;
    PrintWriter out;

    /**
     * SocketClient constructor.
     * @param ip the IP address.
     * @param port the port number.
     */
    public SocketClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    /**
     * Client listener.
     */
    private void setListener() {
        try {
            while (socket.isConnected()) {
                if (!scanner.hasNext()) {
                    throw new IllegalStateException();
                }
                out.println(scanner.nextLine());
                log.writeln("wait answer...");
                log.writeln(in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Init client.
     */
    public void init() {
        try {
            socket = new Socket(InetAddress.getByName(ip), port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            setListener();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SocketClient("127.0.0.1", 777).init();
    }

}