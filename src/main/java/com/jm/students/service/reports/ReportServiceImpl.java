package com.jm.students.service.reports;

import com.jm.students.model.User;
import com.jm.students.model.organization.AbstractOrganization;
import com.jm.students.repository.reports.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Override
    public Long getCountServiceRequestByEngineer(User engineer, AbstractOrganization sc_organization) {
        return reportRepository.getCountServiceRequestByEngineer(engineer, sc_organization);
    }

    @Override
    public Long getCountServiceRequestByClientOrganization(AbstractOrganization cl_organization, AbstractOrganization sc_organization) {
        return reportRepository.getCountServiceRequestByClientOrganization(cl_organization, sc_organization);
    }

    @Override
    public Long getCountAllServiceRequest(AbstractOrganization sc_organization) {
        return reportRepository.getCountAllServiceRequest(sc_organization);
    }
}
