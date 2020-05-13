package multithreading.nonblocking.cache;

public interface Cache {
    void add(Base model);
    void update(Base model);
    void delete(Base model);
}