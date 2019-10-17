package ru.job4j.oop;

public class Battery {
    private int load;
    private String name;

    public Battery(String name, int load) {
        this.name = name;
        this.load = load;
    }

    public void show() {
        System.out.println("Заряд " + this.name + ": " + load);
    }

    public void exchange(Battery another, int value) {
        this.load -= value;
        another.load += value;
        System.out.println("Списать с " + this.name + " " + value + " и добавить " + another.name);
        System.out.println();
    }

    public static void main(String[] args) {
        Battery duracell = new Battery("Duracell", 100);
        duracell.show();
        Battery energizer = new Battery("Energizer", 50);
        energizer.show();

        duracell.exchange(energizer, 10);
        duracell.show();
        energizer.show();

        energizer.exchange(duracell, 20);
        duracell.show();
        energizer.show();
    }

}
