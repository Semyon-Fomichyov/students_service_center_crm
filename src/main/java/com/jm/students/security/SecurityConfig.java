package com.jm.students.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    private final UserDetailsService userDetailsService;
    private final SuccessUserHandler successUserHandler;
    
    public SecurityConfig(
            @Qualifier("UserDetailsServiceImpl") UserDetailsService userDetailsService
            , SuccessUserHandler successUserHandler) {

        this.userDetailsService = userDetailsService;
        this.successUserHandler = successUserHandler;
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/director_page").access("hasRole('ROLE_DIRECTOR')")
                .antMatchers("/manager_page").access("hasRole('ROLE_MANAGER')")
                .antMatchers("/engineer_page").access("hasRole('ROLE_ENGINEER')")
                .antMatchers("/client_employee_page")
                    .access("hasRole('ROLE_CLIENT_EMPLOYEE')")
                .antMatchers("/client_director_page")
                    .access("hasRole('ROLE_CLIENT_DIRECTOR')")
                .anyRequest().authenticated()
                .and().formLogin()
                .successHandler(successUserHandler);

//                .and()
//                .httpBasic();
    }

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
