package tracker.input;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputConsole implements IInput {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String askString(String question) {
        System.out.println(question);
        return scanner.next();
    }

    @Override
    public int askInt(String question) {
        System.out.println(question);
        if (!scanner.hasNextInt()) {
            throw new NoSuchElementException();
        }
        return scanner.nextInt();
    }

    @Override
    public int askInt(String question, int max) {
        int select = askInt(question);
        if (select < 0 || select >= max) {
            throw new IllegalStateException(String.format("Out of about [0..%s]", max));
        }
        return select;
    }

}