package com.jm.students.model;

import com.jm.students.model.organization.AbstractOrganization;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Collections;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String telegramChatId;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE
            , CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private AbstractOrganization organization;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", fetch = FetchType.EAGER)
    List<ServiceRequest> requestList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ServiceRequestComment> comments;


    public void addNewServiceRequest(ServiceRequest request) {
        requestList.add(request);
        request.setCustomer(this);
    }

    public void removeServiceRequest(ServiceRequest request) {
        requestList.remove(request);
        request.setCustomer(null);
    }

    @Override
    public String toString() {
        return "работник сервиса : " +
                " имя : " + firstName + '\'' +
                " фамилия : " + lastName + '\'' +
                " должность : " + role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
