package com.jm.students.service.reports;

import com.jm.students.model.User;
import com.jm.students.model.organization.AbstractOrganization;

public interface ReportService {
    Long getCountServiceRequestByEngineer(User engineer, AbstractOrganization sc_organization) ;
    Long getCountServiceRequestByClientOrganization(AbstractOrganization cl_organization, AbstractOrganization sc_organization);
    Long getCountAllServiceRequest(AbstractOrganization sc_organization);
}
