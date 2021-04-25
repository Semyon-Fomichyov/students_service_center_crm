package com.jm.students.service;


import com.jm.students.repository.AbstractEntityRepository;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class AbstractEntityServiceImpl<T> implements AbstractEntityService<T> {

    private final AbstractEntityRepository<T> abstractEntityRepository;

    public AbstractEntityServiceImpl(AbstractEntityRepository<T> abstractEntityRepository) {
        this.abstractEntityRepository = abstractEntityRepository;
    }

    @Override
    public T findById(Long id) {
        return abstractEntityRepository.findById(id);
    }

    @Override
    public List<T> findAll() {
        return abstractEntityRepository.findAll();
    }

    @Override
    public void remove(T entity) {
        abstractEntityRepository.remove(entity);
    }

    @Override
    public void removeById(Long id) {
        abstractEntityRepository.removeById(id);
    }

    @Override
    public Boolean isExistById(Long id) {
        return abstractEntityRepository.isExistById(id);
    }

    @Override
    public void save(T entity) {
        abstractEntityRepository.save(entity);
    }

    @Override
    public T update(T entity) {
        return abstractEntityRepository.update(entity);
    }
}
