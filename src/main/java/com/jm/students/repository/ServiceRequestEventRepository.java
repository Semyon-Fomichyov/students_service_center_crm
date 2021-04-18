package com.jm.students.repository;

import com.jm.students.model.ServiceRequestEvent;

import java.util.List;

public interface ServiceRequestEventRepository {
    List<ServiceRequestEvent> getAllEvents();
    void newEvent(ServiceRequestEvent event);
    ServiceRequestEvent getEventById(long id);
}
