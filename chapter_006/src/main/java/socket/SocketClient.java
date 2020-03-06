package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
    private String ip;
    private int port;
    private Socket socket;

    public SocketClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void listen(BufferedReader in, PrintWriter out) {
        Scanner scanner = new Scanner(System.in);
        String str = "";
        while (socket.isConnected()) {
            if (!scanner.hasNext()) {
                throw new IllegalStateException();
            }
            out.println(scanner.nextLine());
            System.out.println("wait answer...");
            try {
                str = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(str);
        }
    }

    public void start() {
        try {
            socket = new Socket(InetAddress.getByName(ip), port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            listen(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SocketClient("127.0.0.1", 777).start();
    }

}