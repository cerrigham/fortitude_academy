package it.proactivity.utility;

import it.proactivity.model.Company;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static it.proactivity.utility.SessionUtility.checkSession;
import static it.proactivity.utility.SessionUtility.endSession;

public class CompanyUtility {

    public static Boolean insertOrUpdateCompany(Session session, Long id, String name) {
        if (session == null || name == null) { //TODO check isEmpty
            return false;
        }
        checkSession(session);

        if (id == null) {
            Company company = createACompany(name);
            session.persist(company);
            endSession(session);
            return true;
        } else {
            Company company = getCompanyById(session, id);
            if (company == null) {
                endSession(session);
                return null;
            } else {
                checkForUpdate(company, name);
                session.persist(session);
                endSession(session);
                return true;
            }
        }
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
        if (companyList == null || companyList.size() > 1) {
            endSession(session);
            return false;
        }
        session.delete(companyList.get(0));
        endSession(session);
        return true;
    }

    private static Company createACompany(String name) {
        Company company = new Company();
        company.setName(name);

        return company;
    }

    private static Company getCompanyById(Session session, Long id) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Company> cr = cb.createQuery(Company.class);
        Root<Company> root = cr.from(Company.class);
        cr.select(root).where(cb.equal(root.get("id"), id));

        Query<Company> query = session.createQuery(cr);
        List<Company> companyList = query.getResultList();

        if (companyList == null) {
            return null;
        } else if (companyList.isEmpty()) {
            return null;
        } else if (companyList.size() > 1) {
            return null; //TODO call execption
        } else
            return companyList.get(0);
    }

    private static void checkForUpdate(Company company, String name) {
        if (name != null || !name.isEmpty()) {
            company.setName(name);
        }
    }
}
