package it.proactivity.utility;

import it.proactivity.model.Company;
import it.proactivity.model.CompanyLocation;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import static it.proactivity.utility.SessionUtility.checkSession;
import static it.proactivity.utility.SessionUtility.endSession;

public class CompanyLocationUtility {

    public static CompanyLocation createACompanyLocation(String city, String address) {
        if (city == null || address == null) {
            return null;
        }
        CompanyLocation companyLocation = new CompanyLocation();
        companyLocation.setCity(city);
        companyLocation.setAddress(address);
        return companyLocation;
    }

    private static List<CompanyLocation> checkACompanyLocation(Session session, Long id) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<CompanyLocation> cr = cb.createQuery(CompanyLocation.class);
        Root<CompanyLocation> root = cr.from(CompanyLocation.class);
        cr.select(root).where(cb.equal(root.get("id"), id));

        Query<CompanyLocation> query = session.createQuery(cr);
        List<CompanyLocation> companyLocationList = query.getResultList();
        if (companyLocationList.size() > 1) {
            return null;
        }
        return companyLocationList;
    }

    private static void checkForUpdate(CompanyLocation companyLocation, String city, String address) {
        if (city == null) {
            companyLocation.setCity(city);
        }
        if (address == null) {
            companyLocation.setAddress(address);
        }
    }

    public static Boolean insertOrUpdateCompanyLocation(Session session, String city, String address, Long id) {
        if (session == null || city == null || address == null) {
            return false;
        }
        checkSession(session);

        if (id == null) {
            CompanyLocation companyLocation = createACompanyLocation(city, address);
            session.persist(companyLocation);
            endSession(session);
            return true;
        }
        List<CompanyLocation> companyLocationList = checkACompanyLocation(session, id);
        if (companyLocationList.size() == 0) {
            endSession(session);
        } else {
            CompanyLocation companyLocation = companyLocationList.get(0);
            checkForUpdate(companyLocation, city, address);
            endSession(session);
            return true;
        }
        return false;
    }

    public static Boolean deleteACompanyLocation(Session session, Long id) {
        if (session == null || id == null) {
            return false;
        }
        checkSession(session);
        final String query = "SELECT c " +
                "FROM CompanyLocation c " +
                "WHERE c.id = :id";
        Query<CompanyLocation> companyLocationQuery = session.createQuery(query).setParameter("id", id);
        List<CompanyLocation> companyLocationList = companyLocationQuery.getResultList();
        if (companyLocationList.size() > 1 || companyLocationList == null) {
            endSession(session);
            return false;
        }
        session.delete(companyLocationList.get(0));
        endSession(session);
        return true;
    }
}
