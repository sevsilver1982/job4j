package ru.job4j.tracker;

import java.util.Scanner;

public class InputConsole implements Input {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public int askInt(String question) {
        System.out.print(question);
        return scanner.nextInt();
    }

    @Override
    public String askString(String question) {
        System.out.print(question);
        return scanner.next();
    }

}
