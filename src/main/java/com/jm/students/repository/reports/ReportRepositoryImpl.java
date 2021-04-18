package com.jm.students.repository.reports;

import com.jm.students.model.User;
import com.jm.students.model.organization.AbstractOrganization;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class ReportRepositoryImpl implements ReportRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Long getCountServiceRequestByEngineer(User engineer, AbstractOrganization sc_organization) {
        String queryString = "select count(sr.id) " +
                "from ServiceRequest sr  " +
                "join sr.engineers engineers  " +
                "where engineers.id=:engineer_id and sr.serviceCenterOrganization=:sc_organization " +
                "group by engineers.id ";
        Query query = em.createQuery(queryString);
        query.setParameter("engineer_id", engineer.getId());
        query.setParameter("sc_organization", sc_organization);
         Long res = 0L;
        try {
             res = (Long) query.getSingleResult();
        }catch (NoResultException e){
            return res;
        }
        return res;
    }

    @Override
    public Long getCountServiceRequestByClientOrganization(AbstractOrganization cl_organization, AbstractOrganization sc_organization) {

        String queryString = "select count(sr.id) " +
                "from ServiceRequest sr  " +
                "where sr.serviceCenterOrganization.id=:sc_organization_id and " +
                "sr.clientOrganization.id =:cl_organization_id " +
                "group by sr.clientOrganization ";

        Query query = em.createQuery(queryString);
        query.setParameter("cl_organization_id", cl_organization.getId());
        query.setParameter("sc_organization_id", sc_organization.getId());
        Long res = 0L;
        try {
          res = (Long) query.getSingleResult();
        }catch (NoResultException e){
            return res;
        }
        return res;
    }

    @Override
    public Long getCountAllServiceRequest(AbstractOrganization sc_organization) {
        String queryString = "select count(sr.id) " +
                "from ServiceRequest sr  " +
                "where sr.serviceCenterOrganization.id=:sc_organization_id " +
                "group by sr.clientOrganization ";
        Query query = em.createQuery(queryString);
        query.setParameter("sc_organization_id", sc_organization.getId());
        Long res = 0L;
        try {
            res = (Long) query.getSingleResult();
        }catch (NoResultException e){
            return res;
        }
        return res;
    }
}
