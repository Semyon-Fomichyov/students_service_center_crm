package com.jm.students.model;

import com.jm.students.enums.StatusRequestType;
import com.jm.students.model.organization.ClientOrganization;
import com.jm.students.model.organization.ServiceCenterOrganization;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "service_requests")
public class ServiceRequest {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        private String vehicleNumber;

        private LocalDate dateOfCreate;

        @Enumerated(EnumType.STRING)
        private RequestType requestType;

        @Enumerated(EnumType.STRING)
        private StatusRequestType statusRequestType;

        private String problem;

        @ManyToOne
        private ClientOrganization clientOrganization;

        @ManyToOne
        private ServiceCenterOrganization serviceCenterOrganization;

        @ManyToOne
        private User service_manager;

        @ManyToOne
        private User client_employee;

        @ManyToMany
        private List<User> engineers = new ArrayList<>();

        public void addEngineer(User engineer) {
            engineers.add(engineer);
        }

        public void removeEngineer(User engineer) {
            engineers.remove(engineer);
        }

        @OneToMany(cascade = CascadeType.ALL, mappedBy = "request")
        private List<EquipmentOrder> orders = new ArrayList<>();

        public void addNewEquipmentOrder(EquipmentOrder order) {
            orders.add(order);
            order.setRequest(this);
        }

        public void removeEquipmentOrder(EquipmentOrder order) {
            orders.remove(order);
            order.setRequest(null);
        }
}
