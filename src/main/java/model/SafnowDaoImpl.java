package model;

import controller.PersistanceController;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository
public class SafnowDaoImpl implements SafnowDao {
    public SafnowDaoImpl(){

    }
    @Inject
    PersistanceController persist;
    @Override
    public <T> T getByKey(Class<T> clazz ,Long code) {
        return persist.findByKey(clazz,code);
    }
    @Override
    public void storeUser(User user) {
        persist.store(user);
    }

}
