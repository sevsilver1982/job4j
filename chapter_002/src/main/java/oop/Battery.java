package oop;

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

    public void exchange(Battery another) {
        System.out.println("Списать с " + this.name + " " + this.load + " и добавить " + another.name);
        System.out.println();
        another.load += this.load;
        this.load -= this.load;
    }

    public static void main(String[] args) {
        Battery duracell = new Battery("Duracell", 60);
        Battery energizer = new Battery("Energizer", 40);
        duracell.show();
        energizer.show();

        duracell.exchange(energizer);
        duracell.show();
        energizer.show();

        energizer.exchange(duracell);
        duracell.show();
        energizer.show();
    }

}
