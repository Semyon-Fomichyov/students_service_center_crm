package com.jm.students.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractEntityRepositoryImpl<T> implements AbstractEntityRepository<T> {

    private Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
            .getActualTypeArguments()[0];

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public T findById(Long id) {
        return entityManager.find(clazz, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return entityManager.createQuery("from " + clazz.getName()).getResultList();
    }

    @Override
    public void remove(T entity) {
        entityManager.remove(entity);
    }

    @Override
    public void removeById(Long id) {
        final T entity = findById(id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public Boolean isExistById(Long id) {
        long count = (long) entityManager.createQuery("SELECT COUNT(e) FROM " + clazz.getName() + " e WHERE e.id =: id").setParameter("id", id).getSingleResult();
        return count > 0;
    }

    @Override
    public void save(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public T update(T entity) {
        return entityManager.merge(entity);
    }
}
