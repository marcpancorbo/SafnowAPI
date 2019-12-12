package model;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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
    public User getUser(String code) {
        return null;
    }

    @Override
    public void store(Object object) {
        entityManager.persist(object);
    }

}
