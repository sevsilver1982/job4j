package inout.chat;

import inout.logger.SimpleLogger;

import java.io.*;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

public class SimpleChat {
    private BufferedReader reader;
    private PrintWriter writer;
    private String answers;
    private SimpleLogger log;
    private ChatMode chatMode = ChatMode.NORMAL;

    /**
     * SimpleChat constructor.
     * @param in input stream.
     * @param out output stream.
     * @param answers path to file with answers.
     * @param log chat log stream.
     */
    public SimpleChat(InputStream in, PrintStream out, String answers, PrintStream log) throws IOException {
        this.writer = new PrintWriter(out);
        this.reader = new BufferedReader(new InputStreamReader(in));
        this.answers = answers;
        this.log = new SimpleLogger(log);
    }

    /**
     * Incoming request handler predicate.
     */
    public Predicate<String> testRequest = request -> {
        log.writeln(request);
        switch (getMode(request.trim().toLowerCase())) {
            case NORMAL:
                chatMode = ChatMode.NORMAL;
                break;
            case SILENT:
                chatMode = ChatMode.SILENT;
                break;
            case EXIT:
                chatMode = ChatMode.EXIT;
                return false;
            default:
                break;
        }
        return true;
    };

    /**
     * Outgoing response preparation function.
     */
    Function<String, String> prepareResponse = request -> {
        String preparedResponse;
        if (chatMode == ChatMode.NORMAL) {
            try {
                preparedResponse = String.format("%s\n",
                        new BufferedReader(new FileReader(answers)).lines().skip(
                                (int) (Math.random() * new BufferedReader(new FileReader(answers)).lines().count())
                        ).findFirst().get());
                log.write(preparedResponse);
                return preparedResponse;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return "";
    };

    /**
     * Filter request for chat commands.
     * @param request incoming request.
     * @return Chat mode.
     */
    private ChatMode getMode(String request) {
        return Arrays.stream(ChatMode.values())
                .filter(type -> type.getValue().equals(request))
                .findFirst()
                .orElse(ChatMode.DEFAULT);
    }

    /**
     * Chat init.
     */
    public void init() {
        try {
            new Inout<>(reader, writer, testRequest, prepareResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        /*try(PrintWriter pw = new PrintWriter(System.out))
        {
            pw.println("Hello world!");
        }*/
        /*PrintWriter pw2 = new PrintWriter(System.out, true);
        pw2.println("111111");*/
        new SimpleChat(
                System.in,
                System.out,
                "c:/soft/answers.txt",
                new PrintStream(new File("c:/soft/chat.log"))
        ).init();
    }

}