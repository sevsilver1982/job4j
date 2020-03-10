package inout.chat;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Supplier;

public class Inout {
    private Scanner scanner;
    private ChatMode chatMode = ChatMode.NORMAL;

    public Inout(InputStream in, PrintStream out) {
        this.scanner = new Scanner(in);
        loop(out, new FunctionWriter(), new FunctionReader(answers));
    }

    private ChatMode getMode(String command) {
        return Arrays.stream(ChatMode.values())
                .filter(type -> type.getValue().equals(command))
                .findFirst()
                .orElse(ChatMode.DEFAULT);
    }

    private void loop(PrintStream out, Supplier<String> request, Function<String, String> reply) {
        while (chatMode != ChatMode.EXIT) {

            if (!scanner.hasNext()) {
                throw new IllegalStateException();
            }

            switch (getMode(request.get())) {
                case NORMAL:
                    chatMode = ChatMode.NORMAL;
                    break;
                case SILENT:
                    chatMode = ChatMode.SILENT;
                    break;
                case EXIT:
                    chatMode = ChatMode.EXIT;
                    break;
                default:
                    break;
            }
            if (chatMode == ChatMode.NORMAL) {
                //String s = ;
                out.println(reply.apply("answer"));
            }
        }
    }

}