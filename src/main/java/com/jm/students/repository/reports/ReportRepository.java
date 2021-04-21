package com.jm.students.repository.reports;

import com.jm.students.model.User;
import com.jm.students.model.organization.AbstractOrganization;

public interface ReportRepository {
    Long getCountServiceRequestByEngineer(User engineer, AbstractOrganization sc_organization) ;
    Long getCountServiceRequestByClientOrganization(AbstractOrganization cl_organization, AbstractOrganization sc_organization);
    Long getCountAllServiceRequest(AbstractOrganization sc_organization);
}
