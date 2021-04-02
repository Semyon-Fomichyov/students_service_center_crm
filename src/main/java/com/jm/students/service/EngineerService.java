package com.jm.students.service;

import com.jm.students.model.Engineer;

import java.util.List;

public interface EngineerService {
    void save(Engineer engineer);
    List<Engineer> findAllEngineers();
    Engineer findById(Long id);
    void delete(Long id);
}
