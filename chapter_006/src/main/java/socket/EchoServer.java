package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public void init() throws IOException {
        System.out.println("2");
        try (ServerSocket server = new ServerSocket(9000)) {
            while (true) {
                Socket socket = server.accept();
                System.out.println("2");
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {

                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                    }
                    System.out.println("3");
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write("Hello, dear friend.".getBytes());
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new EchoServer().init();
    }

}