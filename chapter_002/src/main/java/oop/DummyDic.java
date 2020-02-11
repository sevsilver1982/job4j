package oop;

public class DummyDic {

    public String engToRus(String eng) {
        return "Неизвестное слово: " + eng;
    }

    public static void main(String[] args) {
        DummyDic etr = new DummyDic();
        String answer = etr.engToRus("Hello");
        System.out.println(answer);
    }

}
