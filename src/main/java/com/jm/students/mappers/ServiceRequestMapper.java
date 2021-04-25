package com.jm.students.mappers;

import com.jm.students.DTO.ServiceRequestDTO;
import com.jm.students.model.ServiceRequest;
import org.checkerframework.checker.units.qual.C;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = EquipmentOrderMapper.class)
public interface ServiceRequestMapper {

    ServiceRequestMapper SERVICE_REQUEST_MAPPER = Mappers.getMapper(ServiceRequestMapper.class);

    @Mapping(source = "vehicleNumber", target = "vehicleNumber")
    @Mapping(source = "dateOfCreate", target = "dateOfCreate")
    @Mapping(source = "requestType", target = "requestTypeDTO")
    @Mapping(source = "problem", target = "problem")
    @Mapping(source = "customer", target = "customer")
    @Mapping(source = "orders", target = "orders")
    ServiceRequestDTO toServiceRequestDto(ServiceRequest request);

}
