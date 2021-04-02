package com.jm.students.service;

import com.jm.students.dao.EngineerDao;
import com.jm.students.dao.ManagerDao;
import com.jm.students.model.Engineer;
import com.jm.students.model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EngineerServiceImpl implements EngineerService{

    private EngineerDao engineerDao;

    @Autowired
    public EngineerServiceImpl(EngineerDao engineerDao) {
        this.engineerDao = engineerDao;
    }

    @Transactional
    @Override
    public void save(Engineer engineer) {
        engineerDao.save(engineer);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Engineer> findAllEngineers() {
        return engineerDao.findAllEngineers();
    }

    @Transactional(readOnly = true)
    @Override
    public Engineer findById(Long id) {
        return engineerDao.findById(id);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        engineerDao.delete(id);
    }
}
