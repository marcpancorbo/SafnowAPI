package com.safnow.model;

import com.safnow.controller.PersistanceController;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class  SafnowDaoImpl implements SafnowDao {
    private static final Pattern PATTERN = Pattern.compile("(User)(\\d*)$");
    public SafnowDaoImpl() {

    }
    @Inject
    PersistanceController persist;

    @Override
    public User getUser(String identifier) {
        User user = null;
        try {
            user = (User) persist.getEntityManager().createQuery("SELECT u from User u where u.identifier = :identifier").setParameter("identifier", identifier).getSingleResult();
        } catch (NoResultException ignored) {
        }
        return user;
    }


    @Override
    public List<User> findUsers() {
        return (List<User>)persist.getEntityManager().createQuery("SELECT u from User u where u.verificated = true").getResultList();
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
        Alert alert1 = persist.findByKey(Alert.class, user.getAlerts().get(0).getId());
        user.getAlerts().remove(alert1);
        persist.delete(alert1);
       }

    @Override
    public void deleteUser(User user) {
        user = getUser(user.getIdentifier());
        persist.delete(user);
    }

    @Override
    public String getCode() {
        return (String) persist.getEntityManager().createNativeQuery("select code from validation_code order by rand() limit 1").getSingleResult();
    }


    @Override
    public String getNextidentifier() {
        List<User> userList = persist.getEntityManager().createQuery("select u from User u order by identifier desc ").getResultList();
        if (userList.isEmpty()){
            return "User00000";
        }else{
            User user = userList.get(0);
            String identifier = user.getIdentifier();
            Matcher matcher = PATTERN.matcher(identifier);
            if (matcher.matches()){
                String numbers = matcher.group(2);
                int num = Integer.parseInt(numbers);
                return "User"+String.format("%05d", num+1);
            }
        }
        return null;
    }

    @Override
    public User validateCode(String code) {
        User user = (User) persist.getEntityManager().createQuery("select u from User u where u.verificationCode = :verificationCode").setParameter("verificationCode",code).getSingleResult();
        return user;
    }

    @Override
    public  User getUserByPhoneNumber(String phoneNumber) {
        return (User) persist.getEntityManager().createQuery("SELECT a from User a where a.phoneNumber = :phonenumber").setParameter("phonenumber",phoneNumber).getSingleResult();
    }

}
