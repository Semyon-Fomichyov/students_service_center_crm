package com.jm.students.service.util;

import com.jm.students.model.*;
import com.jm.students.model.organization.AbstractOrganization;
import com.jm.students.model.organization.ClientOrganization;
import com.jm.students.model.organization.ServiceCenterOrganization;
import com.jm.students.service.AbstractOrganizationService;
import com.jm.students.service.ServiceRequestService;
import com.jm.students.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

/**
 * Для инициализации БД тестовыми данными необходимо в application.properties
 * проставить свойства spring.jpa.hibernate.ddl-auto=create
 * и spring.profiles.active=dev
 */
@ConditionalOnExpression("'${spring.jpa.hibernate.ddl-auto}'.equals('create')")
//@ConditionalOnProperty(prefix = "spring.jpa.hibernate", value = "ddl-auto", havingValue = "create")
@Profile("dev")
@Component
public class DevProfileSetup {

    private final UserService userService;
    private final ServiceRequestService serviceRequestService;
    private final AbstractOrganizationService organizationService;

    @Autowired
    public DevProfileSetup(UserService userService
            , ServiceRequestService serviceRequestService
            , AbstractOrganizationService organizationService) {

        this.userService = userService;
        this.serviceRequestService = serviceRequestService;
        this.organizationService = organizationService;
    }

    @PostConstruct
    public void initDatabase() {

        AbstractOrganization serviceCenterOrganization =
                saveOrganization(new ServiceCenterOrganization(), "ServiceCenterOrganization");

        AbstractOrganization clientOrganization =
                saveOrganization(new ClientOrganization(), "ClientOrganization");


        saveUser("directorName", "directorLastName"
                , "director","director@mail.ru", serviceCenterOrganization, Role.DIRECTOR);

        saveUser("managerName", "managerLastName"
                , "manager","manager@mail.ru", serviceCenterOrganization, Role.MANAGER);

        saveUser("engineerName", "engineerLastName"
                , "engineer","engineer@mail.ru", serviceCenterOrganization, Role.ENGINEER);

        User clientDirector = saveUser("clientDirectorName", "clientDirectorLastName"
                , "clientDirector","client.director@mail.ru", clientOrganization, Role.CLIENT_DIRECTOR);

        User clientEmployee = saveUser("clientEmployeeName", "clientEmployeeLastName"
                , "clientEmployee","client.employee@mail.ru", clientOrganization, Role.CLIENT_EMPLOYEE);


        saveServiceRequest("vehicleNumber1", LocalDate.now()
                , RequestType.REQUEST_TYPE_1, StatusRequestType.NEW
                , "problem1", clientDirector);

        saveServiceRequest("vehicleNumber2", LocalDate.now()
                , RequestType.REQUEST_TYPE_2, StatusRequestType.IN_WORK
                , "problem2", clientEmployee);
    }

    private AbstractOrganization saveOrganization(AbstractOrganization organization, String name) {

        organization.setName(name);

        organizationService.save(organization);
        return organization;
    }

    private User saveUser(String firstName, String lastName
            , String password, String email, AbstractOrganization organization, Role role) {

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setEmail(email);
        user.setOrganization(organization);
        user.setRole(role);

        userService.save(user);
        return user;
    }

    private void saveServiceRequest(String vehicleNumber
            , LocalDate date, RequestType requestType, StatusRequestType statusRequestType
            , String problem, User customer) {

        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setVehicleNumber(vehicleNumber);
        serviceRequest.setDateOfCreate(date);
        serviceRequest.setRequestType(requestType);
        serviceRequest.setStatusRequestType(statusRequestType);
        serviceRequest.setProblem(problem);
        serviceRequest.setCustomer(customer);

        serviceRequestService.save(serviceRequest);
    }
}
