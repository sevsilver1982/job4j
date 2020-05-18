package inout.chat;

import inout.logger.SimpleLogger;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class SimpleChat {
    private BufferedReader reader;
    private PrintWriter writer;
    private String answers;
    private SimpleLogger log;
    private ChatMode chatMode = ChatMode.NORMAL;

    /**
     * Incoming request handler predicate.
     */
    private Predicate<String> testRequest = request -> {
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
    private Function<String, String> prepareResponse = request -> {
        String preparedResponse;
        if (chatMode == ChatMode.NORMAL) {
            preparedResponse = String.format("%s\n",
                    new BufferedReader(new InputStreamReader(
                            Objects.requireNonNull(SimpleChat.class.getClassLoader().getResourceAsStream(answers))
                    )).lines().skip(
                            (int) (Math.random() * new BufferedReader(new InputStreamReader(
                                    Objects.requireNonNull(SimpleChat.class.getClassLoader().getResourceAsStream(answers))
                            )).lines().count())
                    ).findFirst().get());
            log.write(preparedResponse);
            return preparedResponse;
        }
        return "";
    };

    /**
     * SimpleChat constructor.
     * @param in input stream.
     * @param out output stream.
     * @param answers path to file with answers.
     * @param log chat log stream.
     */
    public SimpleChat(InputStream in, PrintStream out, String answers, PrintStream log) {
        this.writer = new PrintWriter(out);
        this.reader = new BufferedReader(new InputStreamReader(in));
        this.answers = answers;
        this.log = new SimpleLogger(log);
    }

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
        new SimpleChat(
                System.in,
                System.out,
                "answers.txt",
                new PrintStream(new File("c:/soft/chat.log"))
        ).init();
    }

}