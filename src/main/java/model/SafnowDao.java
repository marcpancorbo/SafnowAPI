package model;

public interface SafnowDao {
    User getUser(Long code);
    <T> T getByKey(Class<T> clazz, Long code);
    void store(Object object);
}
