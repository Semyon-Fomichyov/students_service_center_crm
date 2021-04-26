package com.jm.students.repository;

import com.jm.students.model.ServiceRequestEvent;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ServiceRequestEventRepositoryImpl implements ServiceRequestEventRepository{

    @PersistenceContext
    private final EntityManager em;

    public ServiceRequestEventRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<ServiceRequestEvent> findAll() {
        return em.createQuery("from ServiceRequestEvent", ServiceRequestEvent.class).getResultList();
    }

    @Override
    public void save(ServiceRequestEvent event) {
        em.persist(event);
    }

    @Override
    public ServiceRequestEvent findById(long id) {
        return em.find(ServiceRequestEvent.class, id);
    }
}
