package com.jm.students.dao;

import com.jm.students.model.ClientEmployee;

import java.util.List;

public interface ClientEmployeeDao {

    public void addClientEmployee(ClientEmployee clientEmployee);
    public List<ClientEmployee> listClientEmployees();
    public void updateClientEmployee(ClientEmployee clientEmployee);
    public void deleteClientEmployee(Long id);
    public ClientEmployee getClientEmployeeById(Long id);
}
