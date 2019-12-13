package controller;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void store(Object object) {
        entityManager.persist(object);
    }
}
