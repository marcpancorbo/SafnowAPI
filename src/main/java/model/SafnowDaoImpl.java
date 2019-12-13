package model;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class SafnowDaoImpl implements SafnowDao {
    public SafnowDaoImpl(){
        System.out.println("INIT");
    }
    @PersistenceContext()
    EntityManager entityManager;
    @Override
    public User getUser(Long code) {
        return entityManager.find(User.class,code);
    }

    @Override
    public <T> T getByKey(Class<T> clazz ,Long code) {
        return entityManager.find(clazz,code);
    }
    @Override
    public void storeUser(User user) {
        entityManager.persist(user);
    }

}
