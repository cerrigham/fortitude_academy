package it.proactivity.utility;

import it.proactivity.model.Company;
import it.proactivity.model.CompanyLocation;
import it.proactivity.model.HumanResource;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
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

    public static Boolean insertOrUpdateCompanyLocation(Session session, String city, String address, Long id) {
        if (session == null || city == null || address == null) {
            return false;
        }
        checkSession(session);

        if (id == null) {
            Query query = session.createSQLQuery("INSERT INTO company_location (city, address) " +
                    "VALUES (:city, :address)");
            query.setParameter("city", city);
            query.setParameter("address", address);
            int res = query.executeUpdate();
            if (res != 0) {
                return true;
            }
            return false;
        }
        if (id != null) {
            Query query = session.createSQLQuery("UPDATE company_location c " +
                    "WHERE c.id = :id");
            query.setParameter("id", id);
            int res = query.executeUpdate();
            if (res != 0) {
                return true;
            }
            return false;
        }
        endSession(session);
        return true;
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
