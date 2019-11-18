package ru.job4j.tracker;

import java.util.Scanner;

public class InputConsole implements Input {
    private Scanner scanner = new Scanner(System.in);

    public Scanner getScanner() {
        return scanner;
    }

    @Override
    public int askInt(String question) {
        System.out.println(question);
        return scanner.nextInt();
    }

    @Override
    public String askString(String question) {
        System.out.println(question);
        return scanner.next();
    }

    @Override
    public int askInt(String question, int max) {
        int select = askInt(question);
        if (select < 0 || select >= max) {
            throw new IllegalStateException(String.format("Out of about %s > [0, %s]", select, max));
        }
        return select;
    }

}