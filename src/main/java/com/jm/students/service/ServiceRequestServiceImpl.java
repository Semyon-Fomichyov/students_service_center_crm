package com.jm.students.service;

import com.jm.students.model.ServiceRequest;
import com.jm.students.model.ServiceRequestEvent;
import com.jm.students.model.StatusRequestType;
import com.jm.students.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class ServiceRequestServiceImpl extends AbstractEntityServiceImpl<ServiceRequest>
        implements ServiceRequestService {

    private final ServiceRequestRepository requestRepository;

    private final ServiceRequestEventService requestEventService;

    @Autowired
    public ServiceRequestServiceImpl(ServiceRequestRepository requestRepository, ServiceRequestEventService requestEventService) {
        super(requestRepository);
        this.requestRepository = requestRepository;
        this.requestEventService = requestEventService;
    }

    @Override
    public void save(ServiceRequest entity) {
        super.save(entity);
        ServiceRequestEvent event = new ServiceRequestEvent();
        event.setServiceRequest(entity);
        event.setStatusRequestType(StatusRequestType.NEW);
        event.setTimestamp(LocalDateTime.now());
        requestEventService.save(event);
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
        event.setTimestamp(LocalDateTime.now());
        requestEventService.save(event);
    }
}
