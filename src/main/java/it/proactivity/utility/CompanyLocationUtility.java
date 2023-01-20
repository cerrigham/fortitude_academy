package it.proactivity.utility;

import it.proactivity.model.Company;
import it.proactivity.model.CompanyLocation;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.NoSuchElementException;

import static it.proactivity.utility.SessionUtility.checkSession;
import static it.proactivity.utility.SessionUtility.endSession;
import static it.proactivity.utility.Utility.checkIfNullOrEmpty;

public class CompanyLocationUtility {

    public static Boolean insertOrUpdateCompanyLocation(Session session, String city, String address, Long id) {
        if (session == null) {
            return false;
        }
        checkSession(session);

        if (id == null) {
            if (checkIfNullOrEmpty(city) || checkIfNullOrEmpty(address)) {
                endSession(session);
                return false;
            }
            CompanyLocation companyLocation = createACompanyLocation(city, address);
            session.persist(companyLocation);
            endSession(session);
            return true;
        } else {
            CompanyLocation companyLocation = getACompanyLocation(session, id);
            if (companyLocation == null) {
                endSession(session);
                return null;
            } else {
                checkForUpdate(companyLocation, city, address);
                session.persist(session);
                endSession(session);
                return true;
            }
        }
    }

    public static Boolean deleteACompanyLocation(Session session, Long id) {
        if (session == null) {
            return false;
        }
        if (id == null) {
            endSession(session);
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
    private static CompanyLocation createACompanyLocation(String city, String address) {
        CompanyLocation companyLocation = new CompanyLocation();
        companyLocation.setCity(city);
        companyLocation.setAddress(address);
        return companyLocation;
    }

    private static CompanyLocation getACompanyLocation(Session session, Long id) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<CompanyLocation> cr = cb.createQuery(CompanyLocation.class);
        Root<CompanyLocation> root = cr.from(CompanyLocation.class);
        cr.select(root).where(cb.equal(root.get("id"), id));

        Query<CompanyLocation> query = session.createQuery(cr);
        List<CompanyLocation> companyLocationList = query.getResultList();
        if (companyLocationList == null) {
            return null;
        } else if (companyLocationList.isEmpty()) {
            return null;
        } else if (companyLocationList.size() > 1) {
            throw new NoSuchElementException("There are more than one result");
        } else
            return companyLocationList.get(0);
    }

    private static void checkForUpdate(CompanyLocation companyLocation, String city, String address) {
        if (checkIfNullOrEmpty(city)) {
            companyLocation.setCity(city);
        }
        if (checkIfNullOrEmpty(address)) {
            companyLocation.setAddress(address);
        }
    }
}
