package generic;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleArray<T> implements Iterable<T> {
    private T[] objects;
    private int position = 0;

    public SimpleArray(int size) {
        this.objects = (T[]) new Object[size];
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

    public List<T> toList() {
        return Stream.of(objects).limit(position).collect(Collectors.toList());
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
                return pos <= objects.length - 1;
            }

            @Override
            public T next() {
                if (pos > objects.length - 1) {
                    throw new NoSuchElementException();
                }
                return objects[pos++];
            }
        };
    }

}