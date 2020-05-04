package multithreading.examples;

public final class Cache {
    private static Cache cache;

    private synchronized static Cache instOf() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> System.out.println(instOf())).start();
        }
    }

}