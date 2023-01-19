package it.proactivity.utility;

import it.proactivity.model.Company;
import it.proactivity.model.HumanResource;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;

import java.util.List;

import static it.proactivity.utility.SessionUtility.checkSession;
import static it.proactivity.utility.SessionUtility.endSession;

public class HumanResourceUtility {
    public static HumanResource createAHumanResource(String name, String surname,
                                                     String email, String phoneNumber, String vatCode, Boolean isCeo,
                                                     Boolean isCda) {
        if (name == null || surname == null || email == null || phoneNumber == null || vatCode == null || isCeo == null
                || isCda == null) {
            return null;
        }
        HumanResource humanResource = new HumanResource();
        humanResource.setName(name);
        humanResource.setSurname(surname);
        humanResource.setEmail(email);
        humanResource.setPhoneNumber(phoneNumber);
        humanResource.setVatCode(vatCode);
        humanResource.setIsCeo(isCeo);
        humanResource.setIsCda(isCda);
        return humanResource;
    }

    public static Boolean insertOrUpdateHumanResource(Session session, Long id, String name, String surname,
                                                      String email, String phoneNumber, String vatCode, Boolean isCeo,
                                                      Boolean isCda) {
        if (session == null) {
            return false;
        }
        checkSession(session);

        if (id == null) {
            Query query = session.createSQLQuery("INSERT INTO human_resource (name, surname, email, phone_number, " +
                    "vat_code, flag_ceo, flag_cda) " +
                    "VALUES (:name, :surname, :email, :phone_number, :vat_code, :flag_ceo, :flag_cda)");
            query.setParameter("name", name);
            query.setParameter("surname", surname);
            query.setParameter("email", email);
            query.setParameter("phone_number", phoneNumber);
            query.setParameter("vat_code", vatCode);
            query.setParameter("flag_ceo", isCeo);
            query.setParameter("flag_cda", isCda);
            int res = query.executeUpdate();
            if (res != 0) {
                return true;
            }
            return false;
        }
        if (id != null) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaUpdate<HumanResource> update = cb.createCriteriaUpdate(HumanResource.class);
            Root<HumanResource> root = update.from(HumanResource.class);
            update.set("")


        }
    }

    public static Boolean deleteAHumanResource(Session session, Long id) {
        if (session == null || id == null) {
            return false;
        }
        checkSession(session);

        final String query = "SELECT h " +
                "FROM HumanResource h " +
                "WHERE h.id = :id";
        Query<HumanResource> humanResourceQuery = session.createQuery(query).setParameter("id", id);
        List<HumanResource> humanResourceList = humanResourceQuery.getResultList();
        if (humanResourceList.size() > 1 || humanResourceList == null) {
            endSession(session);
            return false;
        }
        session.delete(humanResourceList.get(0));
        endSession(session);
        return true;
    }

}
