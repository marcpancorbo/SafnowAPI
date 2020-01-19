package model;

import java.util.List;

public interface SafnowDao {
    <T> T getByKey(Class<T> clazz, Long identifier);
    User getUser(String identifier);
    List<User> findUsers();
    void storeUser(User user);
    void storeAlert(Alert alert);
    void deleteAlert(Alert alert);
    void deleteUser(User user);
    String getCode();
    String getNextidentifier();
}
