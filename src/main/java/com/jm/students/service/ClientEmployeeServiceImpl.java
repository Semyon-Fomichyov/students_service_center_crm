package com.jm.students.service;

import com.jm.students.dao.ClientEmployeeDao;
import com.jm.students.model.ClientEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientEmployeeServiceImpl implements ClientEmployeeService {

    private final ClientEmployeeDao clientEmployeeDao;

    @Autowired
    public ClientEmployeeServiceImpl(ClientEmployeeDao clientEmployeeDao) {
        this.clientEmployeeDao = clientEmployeeDao;
    }

    @Override
    public void addClientEmployee(ClientEmployee clientEmployee) {
        clientEmployeeDao.addClientEmployee(clientEmployee);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientEmployee> listClientEmployees() {
        return clientEmployeeDao.listClientEmployees();
    }

    @Override
    public void updateClientEmployee(ClientEmployee clientEmployee) {
        clientEmployeeDao.updateClientEmployee(clientEmployee);
    }

    @Override
    public void deleteClientEmployee(Long id) {
        clientEmployeeDao.deleteClientEmployee(id);
    }

    @Override
    @Transactional(readOnly = true)
    public ClientEmployee getClientEmployeeById(Long id) {
        return clientEmployeeDao.getClientEmployeeById(id);
    }
}
