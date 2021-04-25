package com.jm.students.service;

import com.jm.students.model.StatusRequestType;
import com.jm.students.model.ServiceRequest;
import com.jm.students.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServiceRequestServiceImpl extends AbstractEntityServiceImpl<ServiceRequest>
        implements ServiceRequestService {

    private final ServiceRequestRepository requestRepository;

    @Autowired
    public ServiceRequestServiceImpl(ServiceRequestRepository requestRepository) {
        super(requestRepository);
        this.requestRepository = requestRepository;
    }
    /**
     * Метод находит заявку по {@param id},
     * устанавливает этой заявке новый статус {@param statusRequestType}
     * и изменяет заявку в базе
     * @param id идентификатор заявки
     * @param statusRequestType новый статус заявки
     */
    @Override
    public void updateStatusRequestType(long id, StatusRequestType statusRequestType) {
        ServiceRequest serviceRequest = findById(id);
        serviceRequest.setStatusRequestType(statusRequestType);
        update(serviceRequest);
    }
}
