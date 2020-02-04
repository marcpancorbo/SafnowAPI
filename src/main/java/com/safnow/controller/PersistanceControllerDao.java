package com.safnow.controller;

import javax.persistence.EntityManager;
import java.util.List;

public interface PersistanceControllerDao {
    <T> T findByKey(Class<T> clazz, Long key);
    <T>Object selectByKey(Class<T> clazz, String key, Object value );
    void store(Object object);
    void merge(Object object);
    void delete(Object object);
    EntityManager getEntityManager ();
    <T> List<T> find (Class<T> clazz);

}
