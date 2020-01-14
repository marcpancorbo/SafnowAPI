package model;

import org.springframework.dao.DataIntegrityViolationException;

import java.sql.SQLIntegrityConstraintViolationException;

public interface SafnowDao {
    <T> T getByKey(Class<T> clazz, Long identifier);
    User getUser(String identifier);
    void storeUser(User user);
    void storeAlert(Alert alert);
    void deleteAlert(Alert alert);
    String getNextidentifier();
}
