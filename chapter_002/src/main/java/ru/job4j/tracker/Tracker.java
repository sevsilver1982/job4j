package ru.job4j.tracker;

import java.util.Random;

public class Tracker {
    /** Массив для хранение заявок */
    private final Item[] items = new Item[100];
    private static final Random RM = new Random();

    /** Указатель ячейки для новой заявки */
    private int position = 0;

    /**
     * Генерировать уникальный ключ
     * @return Уникальный ключ
     */
    private String generateId() {
        return String.valueOf(RM.nextLong() + System.currentTimeMillis());
    }

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item Новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Редактирование заявки
     * @param id заменяемой заявки
     * @param item Заявка
     * @return
     */
    public boolean replace(String id, Item item) {
        boolean result = false;
        int index = this.getIndexById(id);
        if (index >= 0) {
            this.items[index] = item;
            result = true;
        }
        return result;
    }

    public boolean delete(String id) {
        boolean result = false;
        int index = this.getIndexById(id);
        if (index >= 0) {
            this.items[index] = null;
            for (int i = index; i < this.items.length; i++) {
                if (this.items[i] == null) {
                    for (int j = i + 1; j < this.items.length; j++) {
                        if (this.items[j] != null) {
                            Item item = this.items[j];
                            this.items[j] = this.items[i];
                            this.items[i] = item;
                            result = true;
                            break;
                        }
                    }
                }
            }
        }
        return result;
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
     * @param key
     * @return
     */
    public Item findByName(String key) {
        Item result = null;
        for (Item item: this.items) {
            if (item != null && item.getName().equals(key)) {
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
        Item[] result = new Item[this.position];
        for (int i = 0; i < this.items.length; i++) {
            result[i] = this.items[i];
        }
        return result;
    }

}
