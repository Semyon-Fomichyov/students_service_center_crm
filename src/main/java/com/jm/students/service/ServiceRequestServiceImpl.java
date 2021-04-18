package com.jm.students.service;

import com.jm.students.model.ServiceRequest;
import com.jm.students.model.ServiceRequestEvent;
import com.jm.students.model.StatusRequestType;
import com.jm.students.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class ServiceRequestServiceImpl implements ServiceRequestService {

    private final ServiceRequestRepository requestRepository;
    private final ServiceRequestEventService serviceRequestEventService;

    @Autowired
    public ServiceRequestServiceImpl(ServiceRequestRepository requestRepository,
                                     ServiceRequestEventService serviceRequestEventService) {
        this.requestRepository = requestRepository;
        this.serviceRequestEventService = serviceRequestEventService;
    }

    @Override
    public List<ServiceRequest> getAllServiceRequests() {
        return requestRepository.getAllServiceRequests();
    }

    @Override
    public void saveServiceRequest(ServiceRequest request) {
        requestRepository.saveServiceRequest(request);
        ServiceRequestEvent event = new ServiceRequestEvent();
        event.setServiceRequest(request);
        event.setStatusRequestType(request.getStatusRequestType());
        event.setTimestamp(new Timestamp(System.currentTimeMillis()));
        serviceRequestEventService.newEvent(event);
    }

    @Override
    public void updateServiceRequest(ServiceRequest request) {
        requestRepository.updateServiceRequest(request);
        ServiceRequestEvent event = new ServiceRequestEvent();
        event.setServiceRequest(request);
        event.setStatusRequestType(request.getStatusRequestType());
        event.setTimestamp(new Timestamp(System.currentTimeMillis()));
        serviceRequestEventService.newEvent(event);
    }

    @Override
    public void deleteServiceRequest(ServiceRequest request) {
        requestRepository.deleteServiceRequest(request);
    }

    @Override
    public ServiceRequest getServiceRequestById(long id) {
        return requestRepository.getServiceRequestById(id);
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
        ServiceRequest serviceRequest = getServiceRequestById(id);
        serviceRequest.setStatusRequestType(statusRequestType);
        updateServiceRequest(serviceRequest);
        ServiceRequestEvent event = new ServiceRequestEvent();
        event.setServiceRequest(serviceRequest);
        event.setStatusRequestType(statusRequestType);
        event.setTimestamp(new Timestamp(System.currentTimeMillis()));
        serviceRequestEventService.newEvent(event);
    }
}
