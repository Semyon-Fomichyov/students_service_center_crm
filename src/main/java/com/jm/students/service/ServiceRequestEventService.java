package com.jm.students.service;

import com.jm.students.model.ServiceRequestEvent;

import java.util.List;

public interface ServiceRequestEventService {
    List<ServiceRequestEvent> findAll();
    void save(ServiceRequestEvent event);
    ServiceRequestEvent findById(long id);
}
