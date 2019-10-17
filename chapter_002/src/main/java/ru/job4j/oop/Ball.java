package ru.job4j.oop;

/**
 * Колобок
 */
public class Ball {
    public String name = "Колобок";

    public void onEat(Object eater) {
        System.out.println(
            this.name + ": " + "Я колобок, колобок!\n"
            + this.name + ": " + "По амбару метён,\n"
            + this.name + ": " + "По сусекам скребён,\n"
            + this.name + ": " + "На сметане мешан,\n"
            + this.name + ": " + "В печку сажён,\n"
            + this.name + ": " + "На окошке стужён,\n"
            + this.name + ": " + "Я от дедушки ушёл,\n"
            + this.name + ": " + "Я от бабушки ушёл:");

        if (eater instanceof Hare) {
            System.out.println(this.name + ": " + "И от тебя, зайца, не хитро уйти.\n");
        } else {
            if (eater instanceof Wolf) {
                System.out.println(this.name + ": " + "И от тебя, волка, не хитро уйти.\n");
            } else {
                if (eater instanceof Fox) {
                    System.out.println(this.name + ": " + "И от тебя, лисы, не хитро уйти.\n");
                }
            }
        }
    }

}
