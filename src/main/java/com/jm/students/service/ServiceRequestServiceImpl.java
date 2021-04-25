package com.jm.students.service;

import com.jm.students.model.ServiceRequestEvent;
import com.jm.students.model.StatusRequestType;
import com.jm.students.model.ServiceRequest;
import com.jm.students.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class ServiceRequestServiceImpl extends AbstractEntityServiceImpl<ServiceRequest>
        implements ServiceRequestService {

    private final ServiceRequestRepository requestRepository;

    private final ServiceRequestEventService serviceRequestEventService;

    @Autowired
    public ServiceRequestServiceImpl(ServiceRequestRepository requestRepository, ServiceRequestEventService serviceRequestEventService) {
        super(requestRepository);
        this.requestRepository = requestRepository;
        this.serviceRequestEventService = serviceRequestEventService;
    }
    /**
     * Метод находит заявку по {@param id},
     * устанавливает этой заявке новый статус {@param statusRequestType}
     * и изменяет заявку в базе
     *
     * @param id                идентификатор заявки
     * @param statusRequestType новый статус заявки
     */
    @Override
    public void updateStatusRequestType(long id, StatusRequestType statusRequestType) {
        ServiceRequest serviceRequest = findById(id);
        serviceRequest.setStatusRequestType(statusRequestType);
        update(serviceRequest);
        ServiceRequestEvent event = new ServiceRequestEvent();
        event.setServiceRequest(serviceRequest);
        event.setStatusRequestType(statusRequestType);
        event.setTimestamp(new Timestamp(System.currentTimeMillis()));
        serviceRequestEventService.newEvent(event);
    }}
