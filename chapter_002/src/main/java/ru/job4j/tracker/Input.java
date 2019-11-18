package ru.job4j.tracker;

public interface Input {
    int askInt(String question);
    String askString(String question);
    int askInt(String question, int max);
}
