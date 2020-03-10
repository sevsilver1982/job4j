package socket;

public class SocketClient {
    /*Scanner scanner = new Scanner(System.in);

    private String ip;
    private int port;
    private Socket socket;

    public SocketClient(String ip, int port, BufferedReader in, PrintWriter out) {
        this.ip = ip;
        this.port = port;
    }

    public void listen() {
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
    }*/

    public static void main(String[] args) {
        /*socket = new Socket(InetAddress.getByName("127.0.0.1"), 777);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        new SocketClient(, ).start();*/
    }

}