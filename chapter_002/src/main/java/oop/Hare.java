package oop;

/**
 * Заяц
 */
public class Hare {
    public String name = "Заяц";

    public void tryEat(Ball ball) {
        System.out.println(this.name + ": " + "Колобок, колобок! Я тебя съем");
        ball.onEat(this);
    }

}
