package controller;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class PersistanceController implements PersistanceControllerDao {
    @PersistenceContext()
    private  EntityManager entityManager;
    @Override
    public <T> T findByKey(Class<T> clazz, Long key) {
        return entityManager.find(clazz,key);
    }

    @Override
    public void store(Object object) {
        entityManager.persist(object);
    }
}
