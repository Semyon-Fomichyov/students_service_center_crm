package com.jm.students.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request
            , HttpServletResponse response, Authentication auth) throws IOException {

        Set<String> roles = AuthorityUtils.authorityListToSet(auth.getAuthorities());

        if (roles.contains("ROLE_DIRECTOR")) {
            response.sendRedirect("/director_page");
        } else if (roles.contains("ROLE_MANAGER")) {
            response.sendRedirect("/manager_page");
        } else if (roles.contains("ROLE_ENGINEER")) {
            response.sendRedirect("/engineer_page");
        } else if (roles.contains("ROLE_CLIENT_EMPLOYEE")) {
            response.sendRedirect("/client_employee_page");
        } else {
            response.sendRedirect("/client_director_page");
        }
    }
}
