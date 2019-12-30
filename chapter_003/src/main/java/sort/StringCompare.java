package sort;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        for (int i = 0; i < Math.min(o1.length(), o2.length()); i++) {
            if (o1.charAt(i) != o2.charAt(i)) {
                return o1.charAt(i) - o2.charAt(i);
            }
        }
        return o1.length() - o2.length();
    }

}