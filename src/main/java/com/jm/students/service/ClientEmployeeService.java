package com.jm.students.service;

import com.jm.students.model.ClientEmployee;

import java.util.List;

public interface ClientEmployeeService {

    public void addClientEmployee(ClientEmployee clientEmployee);
    public List<ClientEmployee> listClientEmployees();
    public void updateClientEmployee(ClientEmployee clientEmployee);
    public void deleteClientEmployee(Long id);
    public ClientEmployee getClientEmployeeById(Long id);
}
