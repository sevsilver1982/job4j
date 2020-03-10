package inout.chat;

import inout.logger.SimpleLogger;

import java.io.*;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

public class SimpleChat {
    private Inout inout;
    private InputStream in;
    private PrintStream out;
    private String answers;
    private SimpleLogger log;
    private ChatMode chatMode = ChatMode.NORMAL;

    public SimpleChat(InputStream in, PrintStream out, String answers, PrintStream log) {
        this.in = in;
        this.out = out;
        this.answers = answers;
        this.log = new SimpleLogger(log);
    }

    Function<String, String> prepareResponse = request -> {
        if (chatMode == ChatMode.NORMAL) {
            try {
                return String.format("%s\n",
                        new BufferedReader(new FileReader(answers)).lines().skip(
                                (int) (Math.random() * new BufferedReader(new FileReader(answers)).lines().count())
                        ).findFirst().get());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return "";
    };

    private ChatMode getMode(String request) {
        return Arrays.stream(ChatMode.values())
                .filter(type -> type.getValue().equals(request))
                .findFirst()
                .orElse(ChatMode.DEFAULT);
    }

    /**
     * Incoming request handler predicate.
     */
    public Predicate<String> testRequest = request -> {
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


    public void init() {
        inout = new Inout(in, out, testRequest, prepareResponse);
    }

    public static void main(String[] args) throws IOException {
        new SimpleChat(
                System.in,
                System.out,
                "c:/soft/answers.txt",
                new PrintStream(new File("c:/soft/chat.log"))
        ).init();
    }

}