package animals;

public class Animal {

    public Animal(String name) {
        super();
        System.out.println("load " + name);
    }

    public static void main(String[] args) {
        Animal animal = new Animal("Animal");
        Predator predator = new Predator("Predator");
        Tiger tiger = new Tiger("Tiger");
    }

}
