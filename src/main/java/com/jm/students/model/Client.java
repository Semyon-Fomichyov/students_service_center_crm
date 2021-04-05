package com.jm.students.model;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "client")
@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Client {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_company", nullable = false)
    private String nameOfCompany;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private final Set<ClientEmployee> clientEmployees = new HashSet<>();

    public void addClientEmployee(ClientEmployee clientEmployee) {
        clientEmployees.add(clientEmployee);
        clientEmployee.setClient(this);
    }
    public void removeClientEmployee(ClientEmployee clientEmployee) {
        clientEmployees.remove(clientEmployee);
        clientEmployee.setClient(null);
    }

    public Client(String nameOfCompany, String firstName, String lastName, String address) {
        this.nameOfCompany = nameOfCompany;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }
}