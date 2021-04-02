package com.jm.students.dao;

import com.jm.students.model.Director;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DirectorDaoImpl implements DirectorDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Director director) {
        if (findById(1L) == null) {
            entityManager.persist(director);
        } else {
            entityManager.merge(director);
        }
    }

    @Override
    public List<Director> findAllDirectors() {
        List<Director> directors = entityManager.createQuery("from Director")
                .getResultList();
        return directors;
    }

    @Override
    public Director findById(Long id) {
        return entityManager.find(Director.class, id);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(findById(id));
    }
}
