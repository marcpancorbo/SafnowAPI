package com.safnow.controller;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
@Transactional
public class PersistanceController implements PersistanceControllerDao {
  
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private  EntityManager entityManager;
    @Override
    public <T> T findByKey(Class<T> clazz, Long key) {
        return entityManager.find(clazz,key);
    }

    public <T> List<T> selectByKey(Class<T> clazz, String key, Object value) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> root = cq.from(clazz);
        cq.select(root).where(cb.equal(root.get(key),value));
        TypedQuery q = entityManager.createQuery(cq);
        return q.getResultList();
    }

    @Override
    public void store(Object object) {
        entityManager.persist(object);
    }

    @Override
    public void merge(Object object) {
        entityManager.merge(object);
    }

    @Override
    public void delete(Object object) {
        entityManager.remove(object);
    }

    @Override
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    public <T> List<T> find(Class<T> clazz) {
        return entityManager.createQuery("from "+clazz.getSimpleName()).getResultList();
    }



}
