package com.jm.students.dao;

import com.jm.students.model.Director;
import com.jm.students.model.Engineer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EngineerDaoImpl implements EngineerDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Engineer engineer) {
        if (engineer.getId() == null) {
            entityManager.persist(engineer);
        } else {
            entityManager.merge(engineer);
        }
    }

    @Override
    public List<Engineer> findAllEngineers() {
        List<Engineer> engineer = entityManager.createQuery("from Engineer")
                .getResultList();
        return engineer;
    }

    @Override
    public Engineer findById(Long id) {
        return entityManager.find(Engineer.class, id);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(findById(id));
    }
}
