package com.jm.students.dao;

import com.jm.students.model.ClientEmployee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ClientEmployeeDaoImpl implements ClientEmployeeDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addClientEmployee(ClientEmployee clientEmployee) {
        entityManager.persist(clientEmployee);
    }

    @Override
    public List<ClientEmployee> listClientEmployees() {
        TypedQuery<ClientEmployee> query = entityManager.createQuery("select clientEmployee from ClientEmployee clientEmployee ", ClientEmployee.class);
        return query.getResultList();
    }

    @Override
    public void updateClientEmployee(ClientEmployee clientEmployee) {
        entityManager.merge(clientEmployee);
    }

    @Override
    public void deleteClientEmployee(Long id) {
        entityManager.remove(entityManager.find(ClientEmployee.class, id));
    }

    @Override
    public ClientEmployee getClientEmployeeById(Long id) {
        return entityManager.find(ClientEmployee.class, id);
    }
}
