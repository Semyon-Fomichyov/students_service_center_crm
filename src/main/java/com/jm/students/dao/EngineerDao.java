package com.jm.students.dao;

import com.jm.students.model.Director;
import com.jm.students.model.Engineer;

import java.util.List;

public interface EngineerDao {
    void save(Engineer engineer);
    List<Engineer> findAllEngineers();
    Engineer findById(Long id);
    void delete(Long id);
}
