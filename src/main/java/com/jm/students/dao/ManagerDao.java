package com.jm.students.dao;

import com.jm.students.model.Engineer;
import com.jm.students.model.Manager;

import java.util.List;

public interface ManagerDao {
    void save(Manager manager);
    List<Manager> findAllManagers();
    Manager findById(Long id);
    void delete(Long id);
}
