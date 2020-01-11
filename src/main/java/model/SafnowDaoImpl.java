package model;

import controller.PersistanceController;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.sql.SQLIntegrityConstraintViolationException;

@Repository
public class SafnowDaoImpl implements SafnowDao {
    public SafnowDaoImpl() {

    }
    @Inject
    PersistanceController persist;
    @Override
    public <T> T getByKey(Class<T> clazz, Long identifier) {
        return persist.findByKey(clazz, identifier);
    }

    @Override
    public <T> User getUser(String identifier) {
        User user = null;
        try {
            user = (User) persist.getEntityManager().createQuery("SELECT u from User u where u.identifier = :identifier").setParameter("identifier", identifier).getSingleResult();
        } catch (NoResultException ignored) {
        }
        return user;
    }

    @Override
    public void storeUser(User user) {
        persist.store(user);
    }
    @Override
    public void storeAlert(Alert alert) {
        User user = getUser(alert.getUser().getIdentifier());
        if (user != null){
            alert.setUser(user);
            persist.store(alert);
            user.getAlerts().add(alert);
            persist.store(user);
        }
    }

    @Override
    public void deleteAlert(Alert alert) {
        User user = getUser(alert.getUser().getIdentifier());
        Alert alert1 = getByKey(Alert.class, user.getAlerts().get(0).getId());
        user.getAlerts().remove(alert1);
        persist.delete(alert1);
       }
}
