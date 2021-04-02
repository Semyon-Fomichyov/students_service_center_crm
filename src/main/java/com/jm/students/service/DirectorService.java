package com.jm.students.service;

import com.jm.students.model.Director;

import java.util.List;

public interface DirectorService {
    void save(Director director);
    Director getDirector();
    void delete();
}
