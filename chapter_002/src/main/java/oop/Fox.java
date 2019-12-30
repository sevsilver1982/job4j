package oop;

/**
 * Лиса
 */
public class Fox {
    public String name = "Лиса";

    public void tryEat(Ball ball) {
        System.out.println(this.name + ": " + "Колобок, колобок! Я тебя съем");
        ball.onEat(this);
        System.out.println(this.name + ": " + "Сядь ко мне на мордочку, да пропой ещё разочек... " + " Хрум... Хрум... Хрумм...");
    }

}
