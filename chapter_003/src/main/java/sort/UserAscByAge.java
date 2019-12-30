package sort;

import java.util.Comparator;

public class UserAscByAge implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        return Integer.compare(o1.getAge(), o2.getAge());
    }

}