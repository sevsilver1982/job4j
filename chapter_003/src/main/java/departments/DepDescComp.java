package departments;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        String[] a1 = o1.split("/");
        String[] a2 = o2.split("/");
        for (int i = 0; i < Math.min(a1.length, a2.length); i++) {
            int x = a2[i].compareTo(a1[i]);
            if (x == 0) {
                continue;
            }
            return x;
        }
        return a1.length - a2.length;
    }

}