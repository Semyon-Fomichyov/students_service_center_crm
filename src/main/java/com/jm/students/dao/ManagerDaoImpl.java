package com.jm.students.dao;

import com.jm.students.model.Engineer;
import com.jm.students.model.Manager;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ManagerDaoImpl implements  ManagerDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Manager manager) {
        if (manager.getId() == null) {
            entityManager.persist(manager);
        } else {
            entityManager.merge(manager);
        }
    }

    @Override
    public List<Manager> findAllManagers() {
        List<Manager> manager = entityManager.createQuery("from Manager")
                .getResultList();
        return manager;
    }

    @Override
    public Manager findById(Long id) {
        return entityManager.find(Manager.class, id);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(findById(id));
    }
}
