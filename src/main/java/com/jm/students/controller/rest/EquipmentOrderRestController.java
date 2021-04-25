package com.jm.students.controller.rest;

import com.jm.students.DTO.EquipmentOrderDTO;
import com.jm.students.mappers.EquipmentOrderMapper;
import com.jm.students.model.EquipmentOrder;
import com.jm.students.service.EquipmentOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("equipmentOrders")
public class EquipmentOrderRestController {
    private final EquipmentOrderService equipmentOrderService;
    private final EquipmentOrderMapper equipmentOrderMapper;

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentOrderDTO> getOneEquipmentOrder(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(
                    equipmentOrderMapper.toEquipmentOrderDto(equipmentOrderService.findById(id)
                    ), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<EquipmentOrderDTO>> getAllEquipmentOrders() {
        try {
            List<EquipmentOrder> listOfEquipments = equipmentOrderService.findAll();
            List<EquipmentOrderDTO> listOfEquipmentsDTO = new ArrayList<>();
            for (EquipmentOrder equipment : listOfEquipments) {
                listOfEquipmentsDTO.add(equipmentOrderMapper.toEquipmentOrderDto(equipment));
            }
            return new ResponseEntity<>(listOfEquipmentsDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<EquipmentOrderDTO> addEquipmentOrder(@RequestBody EquipmentOrder equipmentOrder) {
        try {
            equipmentOrderService.save(equipmentOrder);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<EquipmentOrderDTO> updateEquipmentOrder(@RequestBody EquipmentOrder equipmentOrder) {
        try {
            equipmentOrderService.update(equipmentOrder);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<EquipmentOrderDTO> deleteEquipmentOrder(@PathVariable Long id) {
        try {
            equipmentOrderService.removeById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
