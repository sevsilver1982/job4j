package ru.job4j.tracker;

import java.util.Scanner;

public class StartUI {

    private void add(Scanner scanner, Tracker tracker) {
        System.out.println("=== Add new Item ====");
        System.out.print("Enter name: ");
        tracker.add(new Item(scanner.nextLine()));
    }

    private void showAll(Scanner scanner, Tracker tracker) {
        System.out.println("=== Show all Items ====");
        Item[] items = tracker.findAll();
        if (items.length > 0) {
            for (Item item : items) {
                System.out.println("id: " + item.getId() + "; name: " + item.getName());
            }
        } else {
            System.out.println("Items not found");
        }
    }

    private void edit(Scanner scanner, Tracker tracker) {
        System.out.println("=== Edit Item ====");
        System.out.print("Enter item id: ");
        Item item = tracker.findById(scanner.nextLine());
        if (item != null) {
            System.out.print("Enter new item name: ");
            Item itemNew = new Item(scanner.nextLine());
            itemNew.setId(item.getId());
            if (tracker.replace(item.getId(), itemNew)) {
                System.out.println("id: " + item.getId() + "; name: " + item.getName() + " replaced to id: " + itemNew.getId() + "; name: " + itemNew.getName());
            }
        } else {
            System.out.println("Item not found");
        }
    }

    private void delete(Scanner scanner, Tracker tracker) {
        System.out.println("=== Delete Item ====");
        System.out.print("Enter item id: ");
        Item item = tracker.findById(scanner.nextLine());
        if (tracker.delete(item.getId())) {
            System.out.println("id: " + item.getId() + "; name: " + item.getName() + " deleted");
        } else {
            System.out.println("Item not found");
        }
    }

    private void findById(Scanner scanner, Tracker tracker) {
        System.out.println("=== Find Item by Id ====");
        System.out.print("Enter item id: ");
        String id = scanner.nextLine();
        Item item = tracker.findById(id);
        if (item.getId() != null) {
            System.out.println("id: " + item.getId() + "; name: " + item.getName());
        } else {
            System.out.println("Item not found");
        }
    }

    private void findByName(Scanner scanner, Tracker tracker) {
        System.out.println("=== Find items by name ====");
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        Item[] items = tracker.findByName(name);
        if (items.length > 0) {
            for (int i = 0; i < items.length; i++) {
                System.out.println("id: " + items[i].getId() + "; name: " + items[i].getName());
            }
        } else {
            System.out.println("Item not found");
        }
    }

    public void init(Scanner scanner, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            System.out.print("Select: ");
            switch (scanner.nextLine()) {
                case "0":
                    add(scanner, tracker);
                    break;
                case "1":
                    showAll(scanner, tracker);
                    break;
                case "2":
                    edit(scanner, tracker);
                    break;
                case "3":
                    delete(scanner, tracker);
                    break;
                case "4":
                    findById(scanner, tracker);
                    break;
                case "5":
                    findByName(scanner, tracker);
                    break;
                case "6":
                    run = false;
                    break;
                default:
                    System.out.println("Unknown command");
            }
            System.out.println();
        }
    }

    private void showMenu() {
        System.out.println("Menu:");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all Items");
        System.out.println("2. Edit Item");
        System.out.println("3. Delete Item");
        System.out.println("4. Find Item by Id");
        System.out.println("5. Find Item by name");
        System.out.println("6. Exit Program");
    }

    public static void main(String[] args) {
        new StartUI().init(new Scanner(System.in), new Tracker());
    }

}
