package oop;

public class Jukebox {

    public static void main(String[] args) {
        Jukebox box = new Jukebox();
        box.music(0);
        box.music(1);
        box.music(2);
    }

    public void music(int position) {
        switch (position) {
            case (1):
                System.out.println("Пусть бегут неуклюже");
                break;
            case (2):
                System.out.println("Спокойной ночи");
                break;
            default:
                System.out.println("Песня не найдена");
                break;
        }
    }

}