package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

public class Tracker {
    /** Массив для хранение заявок */
    private Item[] items = new Item[100];

    /** Генератор случайных чисел */
    private final Random rm = new Random();

    /** Указатель ячейки для новой заявки */
    private int position = 0;

    /**
     * Генерировать уникальный ключ
     * @return Уникальный ключ
     */
    private String generateId() {
        return String.valueOf(Math.abs(rm.nextLong() + System.currentTimeMillis()));
    }

    /**
     * Добавить
     * @param item Новая заявка
     */
    public Item add(Item item) {
        if (this.items != null) {
            item.setId(this.generateId());
            if (this.position == this.items.length) {
                Item[] tmp = this.findAll();
                this.items = new Item[tmp.length + 1];
                System.arraycopy(tmp, 0, this.items, 0, tmp.length);
            }
            this.items[this.position++] = item;
        }
        return item;
    }

    /**
     * Редактировать
     * @param id заменяемой заявки
     * @param item Заявка
     * @return
     */
    public boolean replace(String id, Item item) {
        boolean result = false;
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                this.items[i] = item;
                result = true;
            }
        }
        return result;
    }

    /**
     * Удалить
     * @param id
     */
    public boolean delete(String id) {
        boolean result = false;
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                result = true;
                System.arraycopy(this.items, i + 1, this.items, i, this.position - i - 1);
                this.position--;
                break;
            }
        }
        return result;
    };

    /**
     * Получить заявку по идентификатору id
     * @param id заявки
     * @return Объект заявки Item
     */
    public Item findById(String id) {
        Item result = new Item();
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                result = this.items[i];
                break;
            }
        }
        return result;
    }

    /**
     * Получить заявку по имени key
     * @param key
     * @return Массив найденных заявок
     */
    public Item[] findByName(String key) {
        Item[] result = new Item[this.position];
        int pos = 0;
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getName().equals(key)) {
                result[pos++] = this.items[i];
            }
        }
        result = Arrays.copyOf(result, pos);
        return result;
    }

    /**
     * Получить все заявки
     * @return Массив объектов Item
     */
    public Item[] findAll() {
        Item[] result = Arrays.copyOf(this.items, this.position);
        return result;
    }

}