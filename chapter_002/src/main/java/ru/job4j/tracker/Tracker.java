package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

public class Tracker {
    /** События */
    private Action[] actions = new Action[0];

    /** Указатель ячейки для новой заявки */
    private int actionsCount = 0;

    /** Массив для хранение заявок */
    private Item[] items = new Item[100];

    /** Указатель ячейки для новой заявки */
    private int itemsCount = 0;

    /** Генератор случайных чисел */
    private final Random rm = new Random();

    public int getActionsCount() {
        return actionsCount;
    }

    public Action getActionByIndex(int actionIndex) {
        return this.actions[actionIndex];
    }

    /**
     * Генерировать уникальный ключ
     * @return Уникальный ключ
     */
    private String generateId() {
        return String.valueOf(Math.abs(rm.nextLong() + System.currentTimeMillis()));
    }

    /**
     * Добавить событие
     * @return
     */
    public Tracker addAction(Action action) {
        if (this.actions != null) {
            if (this.actionsCount == this.actions.length) {
                Action[] tmp = this.findActionAll();
                this.actions = new Action[tmp.length + 1];
                System.arraycopy(tmp, 0, this.actions, 0, tmp.length);
            }
            this.actions[this.actionsCount++] = action;
        }
        return this;
    }

    public Action[] findActionAll() {
        Action[] result = Arrays.copyOf(this.actions, this.actionsCount);
        return result;
    }

    /**
     * Добавить
     * @param item Новая заявка
     */
    public Tracker addItem(Item item) {
        if (this.items != null) {
            item.setId(this.generateId());
            if (this.itemsCount == this.items.length) {
                Item[] tmp = this.findAll();
                this.items = new Item[tmp.length + 1];
                System.arraycopy(tmp, 0, this.items, 0, tmp.length);
            }
            this.items[this.itemsCount++] = item;
        }
        return this;
    }

    /**
     * Редактировать
     * @param idItem заменяемой заявки
     * @param newItem Заявка
     * @return
     */
    public boolean rename(String idItem, Item newItem) {
        boolean result = false;
        for (int i = 0; i < this.itemsCount; i++) {
            if (this.items[i].getId().equals(idItem)) {
                this.items[i] = newItem;
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
        for (int i = 0; i < this.itemsCount; i++) {
            if (this.items[i].getId().equals(id)) {
                result = true;
                System.arraycopy(this.items, i + 1, this.items, i, this.itemsCount - i - 1);
                this.itemsCount--;
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
        for (int i = 0; i < this.itemsCount; i++) {
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
        Item[] result = new Item[this.itemsCount];
        int pos = 0;
        for (int i = 0; i < this.itemsCount; i++) {
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
        Item[] result = Arrays.copyOf(this.items, this.itemsCount);
        return result;
    }

}