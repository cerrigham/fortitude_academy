package it.proactivity.utility;

import it.proactivity.model.Company;
import it.proactivity.model.CompanyLocation;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

import static it.proactivity.utility.SessionUtility.checkSession;
import static it.proactivity.utility.SessionUtility.endSession;

public class CompanyUtility {

    public static Company createACompany(String name) {
        if (name == null) {
            return null;
        }
        Company company = new Company();
        company.setName(name);
        return company;
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
        }
        if (id != null) {

        }
        endSession(session);
        return true;
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
