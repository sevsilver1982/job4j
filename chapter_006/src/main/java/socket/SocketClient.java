package socket;

import inout.chat.Inout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

public class SocketClient {
    Scanner scanner = new Scanner(System.in);

    private String ip;
    private int port;
    private Socket socket;

    public SocketClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    /*public void listen() {
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
    }*/

    /**
     * Incoming request handler predicate.
     */
    public Predicate<String> testRequest = request -> {
        //log.writeln(request);
        return true;
    };

    /**
     * Outgoing response preparation function.
     */
    Function<String, String> prepareResponse = request -> {
        String preparedResponse = request;
        System.out.println(request);
        //log.write(preparedResponse);
        return preparedResponse;
    };

    public void start() {
        try {
            socket = new Socket(InetAddress.getByName(ip), port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            new Inout<>(in, out, testRequest, prepareResponse);
            //listen(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        /*Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 777);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));*/
        new SocketClient("127.0.0.1", 777).start();
    }

}