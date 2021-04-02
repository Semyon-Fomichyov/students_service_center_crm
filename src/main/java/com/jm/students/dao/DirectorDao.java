package com.jm.students.dao;

import com.jm.students.model.Director;

import java.util.List;

public interface DirectorDao {
    void save(Director director);
    List<Director> findAllDirectors();
    Director findById(Long id);
    void delete(Long id);
}
