package examples.array;

public class Defrag {

    public static String[] compress(String[] array) {
        int i = 0;
        for (int index = 0; index < array.length; index++) {
            String cell = array[index];
            if (cell == null) {
                i = index + 1;
                while (i < array.length) {
                    if (array[i] != null) {
                        array[index] = array[i];
                        array[i] = null;
                        break;
                    }
                    i++;
                }
            }
            System.out.print(array[index] + " ");
        }
        return array;
    }

    public static void main(String[] args) {
        String[] input = {"I", null, "wanna", null, "be", null, "compressed"};
        String[] compressed = compress(input);
        System.out.println();
        for (int index = 0; index < compressed.length; index++) {
            System.out.print(compressed[index] + " ");
        }
    }

}