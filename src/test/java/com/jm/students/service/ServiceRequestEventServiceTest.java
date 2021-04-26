package com.jm.students.service;

import com.jm.students.model.ServiceRequestEvent;
import com.jm.students.repository.ServiceRequestEventRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ServiceRequestEventServiceTest {

    @MockBean
    ServiceRequestEventRepository repo;

    @Autowired
    ServiceRequestEventServiceImpl service;

    @Test
    void findAll() {
        List<ServiceRequestEvent> expected = new ArrayList<>();
        ServiceRequestEvent event1 = new ServiceRequestEvent();
        LocalDateTime timestamp1 = LocalDateTime.now();
        event1.setTimestamp(timestamp1);
        ServiceRequestEvent event2 = new ServiceRequestEvent();
        LocalDateTime timestamp2 = LocalDateTime.now();
        event2.setTimestamp(timestamp2);
        expected.add(event1);
        expected.add(event2);

        Mockito.when(repo.findAll()).thenReturn(expected);
        assertEquals(2, service.findAll().size());
    }

    @Test
    void save() {
        ServiceRequestEvent event = new ServiceRequestEvent();
        event.setTimestamp(LocalDateTime.now());
        service.save(event);
        Mockito.verify(repo, Mockito.times(1)).save(event);
    }

    @Test
    void findById() {
        ServiceRequestEvent event = new ServiceRequestEvent();
        event.setTimestamp(LocalDateTime.now());
        Mockito.when(repo.findById(event.getId())).thenReturn(event);
        assertEquals(service.findById(event.getId()), event);
    }
}