package ru.job4j.tracker;

import java.util.Random;

public class Tracker {
    /** Массив для хранение заявок */
    private final Item[] items = new Item[5];
    private final Random RM = new Random();

    /** Указатель ячейки для новой заявки */
    private int position = 0;

    /**
     * Генерировать уникальный ключ
     * @return Уникальный ключ
     */
    private String generateId() {
        return String.valueOf(Math.abs(RM.nextLong() + System.currentTimeMillis()));
    }

    /**
     * Добавить
     * @param item Новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Редактировать
     * @param id заменяемой заявки
     * @param item Заявка
     * @return
     */
    public void replace(String id, Item item) {
        this.items[this.getIndexById(id)] = item;
    }

    /**
     * Удалить
     * @param id
     */
    public void delete(String id) {
        int index = this.getIndexById(id);
        System.arraycopy(this.items, index + 1, this.items, index, this.items.length - index - 1);
        this.items[this.items.length - 1] = null;
        this.position--;
    };

    /**
     * Получить индекс заявки по id
     * @param id заявки
     * @return индекс заявки в массиве
     */
    private int getIndexById(String id) {
        int result = -1;
        for (int i = 0; i < this.items.length; i++) {
            if (this.items[i] != null && this.items[i].getId().equals(id)) {
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     * Получить заявку по идентификатору id
     * @param id заявки
     * @return Объект заявки Item
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item: this.items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Получить заявку по имени key
     * @param name
     * @return
     */
    public Item findByName(String name) {
        Item result = null;
        for (Item item: this.items) {
            if (item != null && item.getName().equals(name)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Получить все заявки
     * @return Массив объектов Item
     */
    public Item[] findAll() {
        Item[] result = new Item[this.items.length];
        for (int i = 0; i < this.items.length; i++) {
            result[i] = this.items[i];
            if (result[i] != null) {
                System.out.println("id:" + result[i].getId() + "; name:" + result[i].getName());
            } else {
                System.out.println("null");
            }
        }
        System.out.println();
        return result;
    }

    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        tracker.add(new Item("item1"));
        tracker.add(new Item("item2"));
        tracker.add(new Item("item3"));
        tracker.add(new Item("item4"));
        tracker.add(new Item("item5"));
        //tracker.findAll();

        tracker.delete(tracker.findByName("item2").getId());
        tracker.delete(tracker.findByName("item4").getId());
        tracker.replace(tracker.findByName("item1").getId(), tracker.findByName("item5"));
        tracker.add(new Item("item10"));
        tracker.replace(tracker.findByName("item3").getId(), tracker.findByName("item10"));
        tracker.findAll();
    }

}
