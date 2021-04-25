package com.jm.students.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum Role implements GrantedAuthority {

    DIRECTOR("ROLE_DIRECTOR"),
    MANAGER("ROLE_MANAGER"),
    ENGINEER("ROLE_ENGINEER"),
    CLIENT_EMPLOYEE("ROLE_CLIENT_EMPLOYEE"),
    CLIENT_DIRECTOR("ROLE_CLIENT_DIRECTOR");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
