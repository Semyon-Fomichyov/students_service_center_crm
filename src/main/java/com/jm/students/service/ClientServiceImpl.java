package com.jm.students.service;

import com.jm.students.dao.ClientDao;
import com.jm.students.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientDao clientDao;

    @Autowired
    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public void addClient(Client client) {
        clientDao.addClient(client);
    }

    @Override
    public List<Client> listClients() {
        return clientDao.listClients();
    }

    @Override
    public void updateClient(Client client) {
        clientDao.updateClient(client);
    }

    @Override
    public void deleteClient(Long id) {
        clientDao.deleteClient(id);
    }

    @Override
    public Client getClientById(Long id) {
        return clientDao.getClientById(id);
    }
}
