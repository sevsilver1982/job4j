package collection;

import java.util.*;

public class Departments {

    public static void main(String[] args) {
        List<String> tmp = Departments.fillGaps(Arrays.asList("K1/SK1", "K1/SK2", "K1/SK1/SSK1", "K1/SK1/SSK2", "K2", "K2/SK1/SSK1", "K2/SK1/SSK2"));
        //Departments.sortAsc(tmp);
        System.out.println(tmp);
        Departments.sortDesc(tmp);
        System.out.println(tmp);
    }

    public static List<String> fillGaps(List<String> deps) {
        HashSet<String> tmp = new HashSet<>();
        for (String value : deps) {
            String start = "";
            for (String dep : value.split("/")) {
                start += dep;
                tmp.add(start);
                start += "/";
            }
        }
        return new ArrayList<>(tmp);
    }

    public static void sortAsc(List<String> orgs) {
        orgs.sort(Comparator.naturalOrder());
    }

    public static void sortDesc(List<String> orgs) {
        orgs.sort(new DepDescComp());
    }

}