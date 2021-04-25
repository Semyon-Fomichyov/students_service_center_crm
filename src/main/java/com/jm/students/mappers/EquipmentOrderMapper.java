package com.jm.students.mappers;

import com.jm.students.DTO.EquipmentOrderDTO;
import com.jm.students.model.EquipmentOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EquipmentOrderMapper {

    EquipmentOrderMapper EQUIPMENT_ORDER_MAPPER = Mappers.getMapper(EquipmentOrderMapper.class);

    @Mapping(source = "equipmentType", target = "equipmentTypeDTO")
    @Mapping(source = "equipmentName", target = "equipmentName")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "request", target = "request", ignore = true)
    EquipmentOrderDTO toEquipmentOrderDto(EquipmentOrder order);
}
