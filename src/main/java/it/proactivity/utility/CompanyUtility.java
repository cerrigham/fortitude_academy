package it.proactivity.utility;

import it.proactivity.model.Company;
import it.proactivity.model.CompanyLocation;
import org.hibernate.Session;
import org.hibernate.annotations.Check;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.event.ListDataEvent;
import java.util.List;

import static it.proactivity.utility.SessionUtility.checkSession;
import static it.proactivity.utility.SessionUtility.endSession;

public class CompanyUtility {

    private static Company createACompany(String name) {
        if (name == null) {
            return null;
        }
        Company company = new Company();
        company.setName(name);
        return company;
    }

    private static List<Company> checkACompany(Session session, Long id) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Company> cr = cb.createQuery(Company.class);
        Root<Company> root = cr.from(Company.class);
        cr.select(root).where(cb.equal(root.get("id"), id));

        Query<Company> query = session.createQuery(cr);
        List<Company> companyList = query.getResultList();
        if (companyList.size() > 1) {
            return null;
        }
        return companyList;
    }

    private static void checkForUpdate(Company company, String name) {
        if (name == null) {
            company.setName(name);
        }
    }

    public static Boolean insertOrUpdateCompany(Session session, String name, Long id) {
        if (session == null || name == null) {
            return false;
        }
        checkSession(session);

        if (id == null) {
            Company company = createACompany(name);
            session.persist(company);
            endSession(session);
            return true;
        }
        List<Company> companyList = checkACompany(session, id);
        if (companyList.size() == 0) {
            endSession(session);
        } else {
            Company company = companyList.get(0);
            checkForUpdate(company, name);
            endSession(session);
            return true;
        }
        return false;
    }

    public static Boolean deleteACompany(Session session, Long id) {
        if (session == null || id == null) {
            return false;
        }
        checkSession(session);
        final String query = "SELECT c " +
                "FROM Company c " +
                "WHERE c.id = :id";
        Query<Company> companyQuery = session.createQuery(query).setParameter("id", id);
        List<Company> companyList = companyQuery.getResultList();
        if (companyList.size() > 1 || companyList == null) {
            endSession(session);
            return false;
        }
        session.delete(companyList.get(0));
        endSession(session);
        return true;
    }
}
