package com.jm.students.service;

import com.jm.students.model.Client;

import java.util.List;

public interface ClientService {

    public void addClient(Client client);
    public List<Client> listClients();
    public void updateClient(Client client);
    public void deleteClient(Long id);
    public Client getClientById(Long id);
}
