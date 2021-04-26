package com.jm.students.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "service_requests_events")
@NoArgsConstructor
@Getter
@Setter
public class ServiceRequestEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime timestamp;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "service_requests_id")
    private ServiceRequest serviceRequest;

    @Enumerated(EnumType.STRING)
    private StatusRequestType statusRequestType;
}
