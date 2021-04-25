package com.jm.students.service;

import com.jm.students.model.StatusRequestType;
import com.jm.students.model.ServiceRequest;

import java.util.List;

public interface ServiceRequestService extends AbstractEntityService<ServiceRequest> {
    void updateStatusRequestType(long id, StatusRequestType statusRequestType);
}
