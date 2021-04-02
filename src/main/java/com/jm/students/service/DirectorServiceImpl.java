package com.jm.students.service;

import com.jm.students.dao.DirectorDao;
import com.jm.students.model.Director;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DirectorServiceImpl implements DirectorService{

    private DirectorDao directorDao;

    @Autowired
    public DirectorServiceImpl(DirectorDao directorDao) {
        this.directorDao = directorDao;
    }

    @Transactional
    @Override
    public void save(Director director) throws IllegalArgumentException {
        if (director.getId().equals(1L)) {
            directorDao.save(director);
        } else {
            throw new IllegalArgumentException();
        }
    }


    @Transactional(readOnly = true)
    @Override
    public Director getDirector() {
        return directorDao.findById(1L);
    }

    @Transactional
    @Override
    public void delete() {
        directorDao.delete(1L);
    }
}
