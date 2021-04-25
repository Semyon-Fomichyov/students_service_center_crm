package com.jm.students;

import com.jm.students.model.EquipmentOrder;
import com.jm.students.model.EquipmentType;
import com.jm.students.model.ServiceRequest;
import com.jm.students.repository.EquipmentOrderRepository;
import com.jm.students.service.EquipmentOrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EquipmentOrderServiceImplTest {

    @MockBean
    EquipmentOrderRepository orderRepository;
    @Autowired
    EquipmentOrderService orderService;


    @Test
    void getAllEquipmentOrders() {

        EquipmentOrder order1 = new EquipmentOrder(1L, EquipmentType.EQUIPMENT_TYPE_2, "test1", 100, new ServiceRequest());
        EquipmentOrder order2 = new EquipmentOrder(2L, EquipmentType.EQUIPMENT_TYPE_1, "test2", 200, new ServiceRequest());
        List<EquipmentOrder> orderList = new ArrayList<>();
        orderList.add(order1);
        orderList.add(order2);

        Mockito.when(orderRepository.findAll()).thenReturn(orderList);
        assertEquals(2, orderService.findAll().size());
    }

    @Test
    void saveEquipmentOrder() {
        EquipmentOrder order3 = new EquipmentOrder(3L, EquipmentType.EQUIPMENT_TYPE_1, "test3", 300, new ServiceRequest());
        orderService.save(order3);
        Mockito.verify(orderRepository, Mockito.times(1)).save(order3);

    }

    @Test
    void updateEquipmentOrder() {
        EquipmentOrder order4 = new EquipmentOrder(4L, EquipmentType.EQUIPMENT_TYPE_1, "test4", 400, new ServiceRequest());
        orderService.update(order4);
        Mockito.verify(orderRepository, Mockito.times(1)).update(order4);

    }

    @Test
    void deleteEquipmentOrder() {
        EquipmentOrder order5 = new EquipmentOrder(5L, EquipmentType.EQUIPMENT_TYPE_1, "test5", 500, new ServiceRequest());
        orderService.remove(order5);
        Mockito.verify(orderRepository, Mockito.times(1)).remove(order5);
    }

    @Test
    void getEquipmentOrderById() {
        EquipmentOrder order6 = new EquipmentOrder(6L, EquipmentType.EQUIPMENT_TYPE_1, "test6", 600, new ServiceRequest());
        Mockito.when(orderRepository.findById(6L)).thenReturn(order6);
        assertEquals(orderService.findById(6L), order6);
    }
}