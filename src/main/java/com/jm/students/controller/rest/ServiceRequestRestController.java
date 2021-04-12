package com.jm.students.controller.rest;

import com.jm.students.DTO.ServiceRequestDTO;
import com.jm.students.mappers.ServiceRequestMapper;
import com.jm.students.model.ServiceRequest;
import com.jm.students.service.ServiceRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("serviceRequests")
public class ServiceRequestRestController {
    private final ServiceRequestService serviceRequestService;
    private final ServiceRequestMapper serviceRequestMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ServiceRequestDTO> getOneServiceRequest(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(
                    serviceRequestMapper.toServiceRequestDto(serviceRequestService.getServiceRequestById(id)
                    ), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<ServiceRequestDTO>> getAllServiceRequests() {
        try {
            List<ServiceRequest> listOfRequests = serviceRequestService.getAllServiceRequests();
            List<ServiceRequestDTO> listOfRequestsDTO = new ArrayList<>();
            for (ServiceRequest request : listOfRequests) {
                listOfRequestsDTO.add(serviceRequestMapper.toServiceRequestDto(request));
            }
            return new ResponseEntity<>(listOfRequestsDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<ServiceRequestDTO> addServiceRequest(@RequestBody ServiceRequest serviceRequest) {
        try {
            serviceRequestService.saveServiceRequest(serviceRequest);
         return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<ServiceRequestDTO> updateServiceRequest(@RequestBody ServiceRequest serviceRequest) {
        try {
            serviceRequestService.updateServiceRequest(serviceRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}