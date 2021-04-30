package com.jm.students.service.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
@Component
public class UtilitySiteUrl {
    public static String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
