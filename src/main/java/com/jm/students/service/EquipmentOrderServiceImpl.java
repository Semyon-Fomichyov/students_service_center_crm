package com.jm.students.service;

import com.jm.students.model.EquipmentOrder;
import com.jm.students.repository.EquipmentOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EquipmentOrderServiceImpl extends AbstractEntityServiceImpl<EquipmentOrder>
        implements EquipmentOrderService {

    private final EquipmentOrderRepository orderRepository;

    @Autowired
    public EquipmentOrderServiceImpl(EquipmentOrderRepository orderRepository) {
        super(orderRepository);
        this.orderRepository = orderRepository;
    }
}
