package model;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Component
public class SafnowDaoImpl implements SafnowDao {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Safnow");
    private EntityManager em = emf.createEntityManager();
    @Override
    public User getUser(String code) {
        return null;
    }

    @Override
    public void store(Object object) {
        em.persist(object);
    }
    
}
