package controller;

public interface PersistanceControllerDao {
    <T> T findByKey(Class<T> clazz, Long key);
    void store(Object object);
}
