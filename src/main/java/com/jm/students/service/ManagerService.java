package com.jm.students.service;

import com.jm.students.model.Manager;

import java.util.List;

public interface ManagerService {
    void save(Manager manager);
    List<Manager> findAllManagers();
    Manager findById(Long id);
    void delete(Long id);
}
