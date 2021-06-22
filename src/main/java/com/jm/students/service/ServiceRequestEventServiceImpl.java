package com.jm.students.service;

import com.jm.students.model.ServiceRequestEvent;
import com.jm.students.repository.ServiceRequestEventRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServiceRequestEventServiceImpl implements  ServiceRequestEventService {

    private final ServiceRequestEventRepository serviceRequestEventRepository;

    public ServiceRequestEventServiceImpl(ServiceRequestEventRepository serviceRequestEventRepository) {
        this.serviceRequestEventRepository = serviceRequestEventRepository;
    }

    @Override
    public List<ServiceRequestEvent> findAll() {
        return serviceRequestEventRepository.findAll();
    }

    @Override
    public void save(ServiceRequestEvent event) {
        serviceRequestEventRepository.save(event);
    }

    @Override
    public ServiceRequestEvent findById(long id) {
        return serviceRequestEventRepository.findById(id);
    }
}
