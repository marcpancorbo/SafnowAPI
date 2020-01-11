package controller;

import javax.persistence.EntityManager;
import java.sql.SQLIntegrityConstraintViolationException;

public interface PersistanceControllerDao {
    <T> T findByKey(Class<T> clazz, Long key);
    void store(Object object);
    void merge(Object object);
    void delete(Object object);
    EntityManager getEntityManager ();
}
