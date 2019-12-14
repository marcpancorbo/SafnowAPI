package controller;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Component
public class PersistanceController implements PersistanceControllerDao {
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
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
