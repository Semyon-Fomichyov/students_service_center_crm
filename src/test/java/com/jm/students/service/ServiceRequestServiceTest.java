package com.jm.students.service;

import com.jm.students.model.ServiceRequest;
import com.jm.students.repository.ServiceRequestRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ServiceRequestServiceTest {

    @MockBean
    ServiceRequestRepository repo;

    @Autowired
    ServiceRequestService service;

    @Test
    void getAllServiceRequests() {
        List<ServiceRequest> expected = new ArrayList<>();
        ServiceRequest sr1 = new ServiceRequest();
        sr1.setVehicleNumber("111");
        ServiceRequest sr2 = new ServiceRequest();
        sr1.setVehicleNumber("222");
        expected.add(sr1);
        expected.add(sr2);

        Mockito.when(repo.findAll()).thenReturn(expected);
        assertEquals(2, service.findAll().size());
    }

    @Test
    void saveServiceRequest() {
        ServiceRequest sr = new ServiceRequest();
        sr.setVehicleNumber("000");
        service.save(sr);
        Mockito.verify(repo, Mockito.times(1)).save(sr);
    }

    @Test
    void updateServiceRequest() {
        ServiceRequest sr = new ServiceRequest();
        sr.setVehicleNumber("000");
        service.update(sr);
        Mockito.verify(repo, Mockito.times(1)).update(sr);
    }

    @Test
    void deleteServiceRequest() {
        ServiceRequest sr = new ServiceRequest();
        sr.setVehicleNumber("000");
        service.remove(sr);
        Mockito.verify(repo, Mockito.times(1)).remove(sr);
    }

    @Test
    void getServiceRequestById() {
        ServiceRequest sr = new ServiceRequest();
        sr.setVehicleNumber("000");
        Mockito.when(repo.findById(sr.getId())).thenReturn(sr);
        assertEquals(service.findById(sr.getId()), sr);
    }
}