package generic.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {
    private T[] objects;
    private int size = 0;

    public SimpleArray(int size) {
        this.objects = (T[]) new Object[size];
    }

    /**
     * Добавляет указанный элемент (model) в первую свободную ячейку
     * @param model
     */
    public void add(T model) {
        objects[size++] = model;
    }

    public T[] getObjects() {
        return Arrays.copyOf(objects, size);
    }

    /**
     * Возвращает элемент, расположенный по указанному индексу;
     * @param index
     */
    public T get(int index) {
        if (size == 0 || index < 0 || index >= size) {
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
        if (size == 0 || index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        objects[index] = model;
    }

    /**
     * Удаляет элемент по указанному индексу, все находящиеся справа элементы при этом необходимо сдвинуть на единицу влево (в середине массива не должно быть пустых ячеек)
     * @param index
     */
    public void remove(int index) {
        if (size == 0 || index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(objects, index + 1, objects, index, size - index - 1);
        size--;
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
                return pos < size;
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