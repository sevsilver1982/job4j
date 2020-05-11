package examples.array;

public class Matrix {

    /**
     * Таблица умножения
     * @param size Размер таблицы
     * @return Таблица умножения
     */
    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = (i + 1) * (j + 1);
            }
        }
        return table;
    }

}