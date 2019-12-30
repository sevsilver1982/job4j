package oop;

/**
 * Волк
 */
public class Wolf {
    public String name = "Волк";

    public void tryEat(Ball ball) {
        System.out.println(this.name + ": " + "Колобок, колобок! Я тебя съем");
        ball.onEat(this);
    }

    public void eat(Girl girl) {

    }

}
