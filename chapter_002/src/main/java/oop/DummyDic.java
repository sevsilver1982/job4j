package oop;

public class DummyDic {

    public String engToRus(String eng) {
        String answer = "Неизвестное слово: " + eng;
        return answer;
    }

    public static void main(String[] args) {
        DummyDic etr = new DummyDic();
        String answer = etr.engToRus("Hello");
        System.out.println(answer);
    }

}
