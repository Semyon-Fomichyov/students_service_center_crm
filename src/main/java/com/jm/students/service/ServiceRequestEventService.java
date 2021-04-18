package com.jm.students.service;

import com.jm.students.model.ServiceRequestEvent;

import java.util.List;

public interface ServiceRequestEventService {
    List<ServiceRequestEvent> getAllEvents();
    void newEvent(ServiceRequestEvent event);
    ServiceRequestEvent getEventById(long id);
}
