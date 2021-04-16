package com.jm.students.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "service_requests_history")
@NoArgsConstructor
@Getter
@Setter
public class ServiceRequestHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE
            , CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn("service_requests_id")
    private ServiceRequest serviceRequest;

    @Enumerated(EnumType.STRING)
    private StatusRequestType statusRequestType;
}
