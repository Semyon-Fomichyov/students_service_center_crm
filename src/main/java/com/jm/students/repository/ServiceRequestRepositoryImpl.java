package com.jm.students.repository;

import com.jm.students.model.ServiceRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ServiceRequestRepositoryImpl extends AbstractEntityRepositoryImpl<ServiceRequest>
        implements ServiceRequestRepository {
}
