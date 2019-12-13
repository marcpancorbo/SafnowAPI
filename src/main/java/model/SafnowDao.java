package model;

public interface SafnowDao {
    <T> T getByKey(Class<T> clazz, Long code);
    void storeUser(User user);
}
