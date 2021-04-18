package com.jm.students.service;

import com.jm.students.model.ServiceRequestEvent;
import com.jm.students.repository.ServiceRequestEventRepository;

import java.util.List;

public class ServiceRequestEventServiceImpl implements  ServiceRequestEventService {

    private final ServiceRequestEventRepository serviceRequestEventRepository;

    public ServiceRequestEventServiceImpl(ServiceRequestEventRepository serviceRequestEventRepository) {
        this.serviceRequestEventRepository = serviceRequestEventRepository;
    }

    @Override
    public List<ServiceRequestEvent> getAllEvents() {
        return serviceRequestEventRepository.getAllEvents();
    }

    @Override
    public void newEvent(ServiceRequestEvent event) {
        serviceRequestEventRepository.newEvent(event);
    }

    @Override
    public ServiceRequestEvent getEventById(long id) {
        return serviceRequestEventRepository.getEventById(id);
    }
}
