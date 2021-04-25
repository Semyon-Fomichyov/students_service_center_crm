package com.jm.students.repository;

import com.jm.students.model.EquipmentOrder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EquipmentOrderRepositoryImpl extends AbstractEntityRepositoryImpl<EquipmentOrder>
        implements EquipmentOrderRepository {
}
