package model;

import java.util.List;

public interface SafnowDao {
    User getUser(String identifier);
    Authorized findAuthorizedByUsername(String username);
    List<User> findUsers();
    void storeUser(User user);
    void storeAlert(Alert alert);
    void deleteAlert(Alert alert);
    void deleteUser(User user);
    String getCode();
    String getNextidentifier();
    User validateCode(String code);
}
