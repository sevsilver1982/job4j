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

    public void listen(BufferedReader in, PrintWriter out) throws IOException {
        Scanner console = new Scanner(System.in);
        do {
            out.println("Hello oracle");
            String str;
            while (!(str = in.readLine()).isEmpty()) {
                System.out.println(str);
            }
        } while (true);
    }

    public boolean start() {
        try {
            socket = new Socket(InetAddress.getByName(ip), port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            listen(in, out);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        new SocketClient("127.0.0.1", 777).start();
    }

}