package generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {
    private T[] objects;
    private int position = 0;

    public SimpleArray(int size) {
        this.objects = (T[]) new Object[size];
    }

    /**
     * Поиск объекта (model)
     * @param model
     */
    public int getIndexByObject(T model) {
        for (int i = 0; i < position; i++) {
            if (objects[i].equals(model)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Добавляет указанный элемент (model) в первую свободную ячейку
     * @param model
     */
    public void add(T model) {
        objects[position++] = model;
    }

    /**
     * Возвращает элемент, расположенный по указанному индексу;
     * @param index
     */
    public T get(int index) {
        if (index >= position) {
            throw new IndexOutOfBoundsException();
        }
        return objects[index];
    }

    /**
     * Заменяет указанным элементом (model) элемент, находящийся по индексу index
     * @param index
     * @param model
     */
    public void set(int index, T model) {
        objects[index] = model;
    }

    /**
     * Удаляет элемент по указанному индексу, все находящиеся справа элементы при этом необходимо сдвинуть на единицу влево (в середине массива не должно быть пустых ячеек)
     * @param index
     */
    public void remove(int index) {
        System.arraycopy(objects, index + 1, objects, index, position - index - 1);
        position--;
    }

    /**
     * Возвращает итератор, предназначенный для обхода данной структуры
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int pos = 0;

            @Override
            public boolean hasNext() {
                return pos < position;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return objects[pos++];
            }
        };
    }

}