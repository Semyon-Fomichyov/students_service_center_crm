package com.jm.students.repository;

import com.jm.students.model.ServiceRequestEvent;

import java.util.List;

public interface ServiceRequestEventRepository {
    List<ServiceRequestEvent> findAll();
    void save(ServiceRequestEvent event);
    ServiceRequestEvent findById(long id);
}
