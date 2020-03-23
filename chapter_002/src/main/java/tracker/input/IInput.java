package tracker.input;

public interface IInput {
    String askString(String question);
    int askInt(String question);
    int askInt(String question, int max);
}