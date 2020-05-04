package multithreading.collections;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SingleLockListTest {

    @Test
    public void add() throws InterruptedException {
        SingleLockList<Integer> singleLockList = new SingleLockList<>();
        Thread thread1 = new Thread(() -> singleLockList.add(1));
        Thread thread2 = new Thread(() -> singleLockList.add(2));
        Thread thread3 = new Thread(() -> singleLockList.add(3));
        Thread thread4 = new Thread(() -> singleLockList.add(4));
        Thread thread5 = new Thread(() -> singleLockList.add(5));
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();

        Set<Integer> result;
        result = new TreeSet<>();
        Iterator<Integer> it = singleLockList.iterator();
        result.add(it.next());
        result.add(it.next());
        thread4.start();
        thread5.start();
        result.add(it.next());
        thread4.join();
        thread5.join();
        assertEquals(Set.of(1, 2, 3), result);

        result = new TreeSet<>();
        singleLockList.iterator().forEachRemaining(result::add);
        assertEquals(Set.of(1, 2, 3, 4, 5), result);
    }

}