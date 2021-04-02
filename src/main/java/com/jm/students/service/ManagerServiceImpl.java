package com.jm.students.service;

import com.jm.students.dao.DirectorDao;
import com.jm.students.dao.ManagerDao;
import com.jm.students.model.Director;
import com.jm.students.model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManagerServiceImpl implements  ManagerService{

    private ManagerDao managerDao;

    @Autowired
    public ManagerServiceImpl(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    @Transactional
    @Override
    public void save(Manager manager) {
        managerDao.save(manager);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Manager> findAllManagers() {
        return managerDao.findAllManagers();
    }

    @Transactional(readOnly = true)
    @Override
    public Manager findById(Long id) {
        return managerDao.findById(id);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        managerDao.delete(id);
    }
}
