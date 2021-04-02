package com.jm.students.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "directors")
@EqualsAndHashCode
@ToString
@Data
public class Director {

    @Id
    private Long id;
    private String firstName;
    private String lastName;

    public Director() {
        this.id = 1L;
    }

    public Director(String firstName, String lastName) {
        this.id = 1L;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
