package com.jm.students.dao;

import com.jm.students.model.Client;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ClientDaoImpl implements ClientDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addClient(Client client) {
        entityManager.persist(client);
    }

    @Override
    public List<Client> listClients() {
        TypedQuery<Client> query = entityManager.createQuery("select client from Client client", Client.class);
        return query.getResultList();
    }

    @Override
    public void updateClient(Client client) {
        entityManager.merge(client);
    }

    @Override
    public void deleteClient(Long id) {
        entityManager.remove(entityManager.find(Client.class, id));
    }

    @Override
    public Client getClientById(Long id) {
        return entityManager.find(Client.class, id);
    }
}
